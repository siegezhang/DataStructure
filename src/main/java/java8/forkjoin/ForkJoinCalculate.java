package java8.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

  // 临界值
  private static final long THRESHOLD = 10000L;
  private final long start;
  private final long end;

  public ForkJoinCalculate(long start, long end) {
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {
    long length = end - start;

    if (length <= THRESHOLD) {
      long sum = 0;
      for (long i = start; i <= end; i++) {
        sum += i;
      }
      return sum;
    } else {
      long middle = (start + end) / 2;
      ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
      left.fork(); // 拆分，并将该子任务压入线程队列
      ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
      right.fork();
      return left.join() + right.join();
    }
  }
}
