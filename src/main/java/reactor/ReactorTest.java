package reactor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {
  /**
   * 如果省略subscribeOn,则在subscribe方法被调用线程执行
   *
   * @throws InterruptedException
   */
  @Test
  public void test1() throws InterruptedException {
    Flux.just("hello", "world").subscribe(System.out::println);
    Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
    Flux.empty().subscribe(System.out::println);

    Flux.range(1, 10).subscribe(System.out::println);
    Flux<Long> flux = Flux.interval(Duration.of(1, ChronoUnit.SECONDS));

    flux.subscribe(System.out::println);

    // This line keeps the program running to receive emissions
    while (true) {
      // Add logic to handle termination if needed (optional)
      Thread.sleep(Long.MAX_VALUE);
    }
  }

  @Test
  public void test2() {
    Flux.generate(
            sink -> {
              sink.next("Hello");
              sink.complete();
            })
        .subscribe(System.out::println);

    final Random random = new Random();
    Flux.generate(
            ArrayList::new,
            (list, sink) -> {
              int value = random.nextInt();
              list.add(value);
              sink.next(value);
              if (list.size() == 10) sink.complete();
              return list;
            })
        .subscribe(System.out::println);
  }

  @Test
  public void test3() {
    Flux.create(
            sink -> {
              for (int i = 0; i < 10; i++) {
                sink.next(i);
              }
            })
        .subscribe(System.out::println);
  }

  @Test
  public void test4() {
    Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
    Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
    Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
  }

  @Test
  public void test5() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .subscribe(System.out::println, Throwable::printStackTrace);

    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .onErrorReturn(0)
        .subscribe(System.out::println);

    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .onErrorResume(
            e -> {
              if (e instanceof IllegalStateException) return Mono.just(0);
              else if (e instanceof IllegalArgumentException) return Mono.just(-1);
              return Mono.empty();
            })
        .subscribe(System.out::println);

    Flux.just(3, 4)
        .concatWith(Mono.error(IllegalStateException::new))
        .retry(1)
        .subscribe(System.out::println);
  }

  @Test
  public void test6() {
    Flux.create(
            sink -> {
              sink.next(Thread.currentThread().getName());
              sink.complete();
            })
        .publishOn(Schedulers.single())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .publishOn(Schedulers.elastic())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .subscribeOn(Schedulers.parallel())
        .toStream()
        .forEach(System.out::println);
  }

  /** 只有第一个subscribeOn起作用,后续的subscribeOn不起作用 */
  @Test
  public void test7() {
    Flux.just("tom")
        .map(
            s -> {
              System.out.println("(to length) at [" + Thread.currentThread() + "]");
              return s.length();
            })
        .subscribeOn(Schedulers.newSingle("source1"))
        .subscribeOn(Schedulers.newSingle("source2"))
        .subscribeOn(Schedulers.newSingle("source3"))
        .subscribeOn(Schedulers.newSingle("source4"))
        .subscribe(System.out::println);
  }

  /**
   * <code>
   * Tue Jan 18 18:57:51 CST 2022
   * Tue Jan 18 18:57:51 CST 2022
   * Tue Jan 18 18:57:51 CST 2022
   * Tue Jan 18 18:57:56 CST 2022
   * </code> 一个使用Mono.just创建，一个用Mono.defer创建，然后分别通过lambda表达式订阅这两个publisher，
   * 可以看到两个输出的时间都是18:57:51，延迟5秒钟后重新订阅，Mono.just创建的数据源时间没变，
   * 但是Mono.defer创建的数据源时间相应的延迟了5秒钟，原因在于Mono.just会在声明阶段构造Date对象，只
   * 创建一次，但是Mono.defer却是在subscribe阶段才会创建对应的Date对象，每次调用subscribe方法都会创建Date对象
   */
  @Test
  public void defer() {
    // 声明阶段创建DeferClass对象

    Mono<Date> m1 = Mono.just(new Date());
    Mono<Date> m2 = Mono.defer(() -> Mono.just(new Date()));
    m1.subscribe(System.out::println);
    m2.subscribe(System.out::println);
    // 延迟5秒钟
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    m1.subscribe(System.out::println);
    m2.subscribe(System.out::println);
  }
}
