package feature.java19;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 虚拟线程是 JDK 而不是 OS 实现的轻量级线程(Lightweight Process，LWP），许多虚拟线程共享同一个操作系统线程，虚拟线程的数量可以远大于操作系统线程的数量。
 * 虚拟线程在其他多线程语言中已经被证实是十分有用的，比如 Go 中的 Goroutine、Erlang 中的进程。
 * 虚拟线程避免了上下文切换的额外耗费，兼顾了多线程的优点，简化了高并发程序的复杂，可以有效减少编写、维护和观察高吞吐量并发应用程序的工作量。
 */
public class Java19Test {
  @Test
  public void testVirtualThread() {
    // 输出线程ID 包括虚拟线程和系统线程 Thread.getId() 从jdk19废弃
    Runnable runnable = () -> System.out.println(Thread.currentThread().threadId());
    // 创建虚拟线程
    Thread thread = Thread.ofVirtual().name("testVT").unstarted(runnable);
    thread.start();
    // 创建虚平台线程
    Thread testPT = Thread.ofPlatform().name("testPT").unstarted(runnable);
    testPT.start();
  }

  @Test
  public void testThreadJoin() throws Exception {
    Runnable runnable =
        () -> {
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        };
    Thread thread = Thread.startVirtualThread(runnable);
    // 等待虚拟线程结束
    thread.join();
  }

  @Test
  public void testExecutors() {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      executor.submit(() -> System.out.println("hello"));
    }
  }

  @Test
  public void testExecutors1() {
    // 记录系统线程数
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(
        () -> {
          ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
          ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
          System.out.println(threadInfo.length + " os thread");
        },
        1,
        1,
        TimeUnit.SECONDS);

    long l = System.currentTimeMillis();
    try (var executor = Executors.newFixedThreadPool(200)) {
      IntStream.range(0, 10000)
          .forEach(
              i ->
                  executor.submit(
                      () -> {
                        Thread.sleep(Duration.ofSeconds(1));
                        System.out.println(i);
                        return i;
                      }));
    }
    System.out.printf("耗时：%dms\n", System.currentTimeMillis() - l);
  }

  /**
   * 使用虚拟线程比平台线程要快很多，并且使用的系统线程资源要更少。 虚拟线程的存在是为了提供更高的吞吐量，而不是速度（更低的延迟）
   * 虚拟线程有助于提高服务端应用程序的吞吐量，因为此类应用程序有大量并发，而且这些任务通常会有大量的 IO 等待。
   */
  @Test
  public void testExecutors2() {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(
        () -> {
          ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
          ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
          System.out.println(threadInfo.length + " os thread");
        },
        10,
        10,
        TimeUnit.MILLISECONDS);

    long l = System.currentTimeMillis();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 10000)
          .forEach(
              i -> {
                executor.submit(
                    () -> {
                      Thread.sleep(Duration.ofSeconds(1));
                      System.out.println(i);
                      return i;
                    });
              });
    }
    System.out.printf("耗时：%dms\n", System.currentTimeMillis() - l);
  }
}
