package reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class ReactorTest {
  @Test
  public void test1() {
    Flux.just("hello", "world").subscribe(System.out::println);
    Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
    Flux.empty().subscribe(System.out::println);

    Flux.range(1, 10).subscribe(System.out::println);
    Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
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
        .subscribe(System.out::println, System.err::println);

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

    Flux.just(1, 2)
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
}
