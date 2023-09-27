package feature.java8.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

  @Test
  public void test1() {
    long start = System.currentTimeMillis();
    ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 10000000000L);
    long sum = pool.invoke(task);
    System.out.println(sum);
    long end = System.currentTimeMillis();
    System.out.println("耗费的时间为: " + (end - start));
  }

  // java8 优化并行流
  @Test
  public void test3() {
    long start = System.currentTimeMillis();
    Long sum = LongStream.rangeClosed(0L, 10000000000L).parallel().sum();
    System.out.println(sum);
    long end = System.currentTimeMillis();
    System.out.println("耗费的时间为: " + (end - start));
  }
}
