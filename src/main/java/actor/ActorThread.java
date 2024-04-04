package actor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 核心功能很简单, 就是每个Actor是一个线程处理自身事件队列的循环, Actor中定义的数据和方法只在该线程中访问,
 * Actor实现的接口可公开出来供其它Actor自动异步访问(通过动态代理).
 *
 * <p>1. 各Actor应该只公开接口, 而实现类不要暴露出来, 避免被误用.
 *
 * <p>2. 接口方法通常返回CompletableFuture类型,并在方法返回时返回 CompletableFuture.completedFuture(结果).
 *
 * <p>3. 如果没按2的方法实现返回, 那么调用方获得的future只能得到null结果.
 *
 * <p>4. 调用方得到future时很可能结果还没得到, 所以通常要用thenAccept等方法指定结果产生时的后续处理,或者继续并行执行其它代码.
 *
 * <p>5. 虽然接口方法能自动转到各自的Actor线程中运行, 但引用类型参数所引用的变量是共享的, 所以调用方和被调用方怎么访问参数(尤其是写操作)要好自为之.
 * 在严格隔离的场合需要对参数做序列化和反序列化.
 */
@SuppressWarnings("MethodMayBeStatic")
public abstract class ActorThread extends Thread {
  private static final class Message {
    final CompletableFuture<Object> future = new CompletableFuture<>();
    final ActorThread threadFrom;
    final Method method;
    final Object[] params;
    Object result;

    Message(Thread threadFrom, Method method, Object[] params) {
      this.threadFrom = threadFrom instanceof ActorThread ? (ActorThread) threadFrom : null;
      this.method = method;
      this.params = params;
    }
  }

  private static final ConcurrentHashMap<Class<?>, Object> actorMap = new ConcurrentHashMap<>();
  private final LinkedBlockingQueue<Message> msgQueue = new LinkedBlockingQueue<>();

  protected ActorThread() {
    final Class<? extends ActorThread> cls = getClass();
    final Class<?>[] intfs = cls.getInterfaces();
    if (intfs.length <= 0) throw new IllegalStateException("no actor in " + cls.getName());
    final Object proxy =
        Proxy.newProxyInstance(
            cls.getClassLoader(),
            intfs,
            (__, method, params) -> {
              final Message msg = new Message(Thread.currentThread(), method, params);
              msgQueue.put(msg);
              //noinspection SuspiciousInvocationHandlerImplementation
              return msg.future;
            });
    for (final Class<?> intf : intfs) {
      final Object oldActor = actorMap.putIfAbsent(intf, proxy);
      if (oldActor != null)
        throw new IllegalStateException(
            "duplicated actor "
                + intf.getName()
                + " in "
                + cls.getName()
                + " and "
                + oldActor.getClass().getName());
    }
    start();
  }

  public static void init(Class<? extends ActorThread> actorClass) throws Exception {
    final Constructor<? extends ActorThread> ctor = actorClass.getDeclaredConstructor();
    ctor.setAccessible(true);
    ctor.newInstance();
  }

  public static <I> I getActor(Class<I> interfaceClass) {
    //noinspection unchecked
    return (I) actorMap.get(interfaceClass);
  }

  protected boolean onInterrupted() {
    return true;
  }

  protected void onException(Throwable e) {
    e.printStackTrace();
  }

  @Override
  public void run() {
    for (; ; ) {
      try {
        //noinspection InfiniteLoopStatement
        for (; ; ) {
          final Message msg = msgQueue.take();
          Object res = msg.result;
          if (res == null) {
            res = msg.method.invoke(this, msg.params);
            if (res instanceof CompletableFuture) res = ((CompletableFuture<?>) res).getNow(null);
            if (msg.threadFrom != null) {
              msg.result = res != null ? res : msgQueue;
              msg.threadFrom.msgQueue.put(msg);
            } else msg.future.complete(res);
          } else msg.future.complete(res != msgQueue ? res : null);
        }
      } catch (InterruptedException e) {
        //noinspection ResultOfMethodCallIgnored
        interrupted(); // clear status
        if (onInterrupted()) break;
      } catch (Throwable e) {
        onException(e);
      }
    }
  }
}
