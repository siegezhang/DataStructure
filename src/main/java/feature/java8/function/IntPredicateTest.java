//package feature.java8.function;
//
//import io.reactivex.rxjava3.core.Observable;
//import one.util.streamex.StreamEx;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.function.Function;
//import java.util.function.IntPredicate;
//import java.util.stream.IntStream;
//
//public class IntPredicateTest {
//  public static <U> Function<U, IntPredicate> and(Function<U, IntPredicate>... filters) {
//    return user -> {
//      IntPredicate andPredicate = _ -> true;
//      for (Function<U, IntPredicate> filter : filters) {
//        andPredicate = andPredicate.and(filter.apply(user));
//      }
//      return andPredicate;
//    };
//  }
//
//  public static StreamEx<Integer> sieve(StreamEx<Integer> input, IntPredicate isPrime) {
//    return input.headTail(
//        (head, tail) ->
//            isPrime.test(head)
//                ? sieve(tail, isPrime.and(n -> n % head != 0)).prepend(head)
//                : sieve(tail, isPrime));
//  }
//
//  @Test
//  public void test() {
//    IntStream stream = IntStream.range(1, 10);
//    List<Integer> oddNumbers = stream.filter(argument -> argument % 2 == 1).boxed().toList();
//  }
//
//  @Test
//  public void test1() {
//    IntPredicate i = (x) -> x < 0;
//    IntPredicate j = (x) -> x == 0;
//    System.out.println(i.and(j).test(123));
//  }
//
//  @Test
//  public void test2() {
//    final IntPredicate[] p = {(x) -> true};
//    IntStream primesStream =
//        IntStream.iterate(2, n -> n + 1)
//            .filter(i -> p[0].test(i))
//            .peek(i -> p[0] = p[0].and(v -> v % i != 0));
//    Observable<IntStream> primes = Observable.fromAction(() -> primesStream.iterator());
//    primes.take(10).forEach((x) -> System.out.println(x.toString()));
//  }
//
//  @Test
//  public void test3() {
//    sieve(StreamEx.iterate(2, x -> x + 1), i -> true).limit(1000).forEach(System.out::println);
//  }
//}
