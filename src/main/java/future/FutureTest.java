package future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/** <a href="https://mp.weixin.qq.com/s/6j7gxSXRouv2_R2awlQU9A">FutureTest</a> */
public class FutureTest {
  @Test
  public void test1() throws Exception {
    // 创建异步执行任务:
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<Double> cf =
        executorService.submit(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (false) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
              }
            });
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成,如果已完成则直接返回结果
    // 如果执行任务异常，则get方法会把之前捕获的异常重新抛出
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test2() throws Exception {
    // 创建异步执行任务，有返回值
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (true) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
              }
            });
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test3() throws Exception {
    // 创建异步执行任务，无返回值
    CompletableFuture<Void> cf =
        CompletableFuture.runAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (false) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
              }
            });
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test4() throws Exception {
    ForkJoinPool pool = new ForkJoinPool();
    // 创建异步执行任务:
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (true) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
              }
            },
            pool);
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test5() throws Exception {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    // 创建异步执行任务:
    CompletableFuture<Void> cf =
        CompletableFuture.runAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (false) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
              }
            },
            executorService);
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test6() throws Exception {
    ForkJoinPool pool = new ForkJoinPool();
    // 创建异步执行任务:
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              System.out.println(
                  Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
              return 1.2;
            },
            pool);
    // cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
    // thenApply这里实际创建了一个新的CompletableFuture实例
    CompletableFuture<String> cf2 =
        cf.thenApply(
            (result) -> {
              System.out.println(
                  Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              System.out.println(
                  Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
              return "test:" + result;
            });
    System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
    System.out.println("run result->" + cf2.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test7() throws Exception {
    ForkJoinPool pool = new ForkJoinPool();
    // 创建异步执行任务:
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              System.out.println(
                  Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
              return 1.2;
            },
            pool);
    // cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
    CompletableFuture cf2 =
        cf.thenApply(
                (result) -> {
                  System.out.println(
                      Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
                  try {
                    Thread.sleep(2000);
                  } catch (InterruptedException e) {
                  }
                  System.out.println(
                      Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
                  return "test:" + result;
                })
            .thenAccept(
                (result) -> { // 接收上一个任务的执行结果作为入参，但是没有返回值
                  System.out.println(
                      Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
                  try {
                    Thread.sleep(2000);
                  } catch (InterruptedException e) {
                  }
                  System.out.println(result);
                  System.out.println(
                      Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
                })
            .thenRun(
                () -> { // 无入参，也没有返回值
                  System.out.println(
                      Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
                  try {
                    Thread.sleep(2000);
                  } catch (InterruptedException e) {
                  }
                  System.out.println("thenRun do something");
                  System.out.println(
                      Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
                });
    System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
    // cf2 等待最后一个thenRun执行完成
    System.out.println("run result->" + cf2.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }
}
