package actor;

import java.util.concurrent.CompletableFuture;

interface IActorA {
  void func();
}

interface IActorB {
  CompletableFuture<Integer> add(int a, int b);
}

final class ActorA extends ActorThread implements IActorA {
  private ActorA() {
    setName("ActorA");
  }

  @Override
  public void func() {
    System.out.println(Thread.currentThread().getName() + ": invoke add begin");
    getActor(IActorB.class)
        .add(123, 456)
        .thenAccept(
            r -> {
              System.out.println(Thread.currentThread().getName() + ": invoke add result = " + r);
              System.exit(0);
            });
    System.out.println(Thread.currentThread().getName() + ": invoke add end");
  }
}

final class ActorB extends ActorThread implements IActorB {
  private ActorB() {
    setName("ActorB");
  }

  @Override
  public CompletableFuture<Integer> add(int a, int b) {
    System.out.println(Thread.currentThread().getName() + ": in add");
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return CompletableFuture.completedFuture(a + b);
  }
}

public final class ActorTest {
  public static void main(String[] args) throws Exception {
    ActorThread.init(ActorA.class);
    ActorThread.init(ActorB.class);
    System.out.println(Thread.currentThread().getName() + ": begin to func");
    ActorThread.getActor(IActorA.class).func();
  }
}
