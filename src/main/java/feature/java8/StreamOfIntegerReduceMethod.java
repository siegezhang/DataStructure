package feature.java8;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class StreamOfIntegerReduceMethod {
  @Test
  public void test() {
    System.out.println("Create Stream of Integers");
    Stream<Integer> streamOfIntegers = Stream.of(1, 2, 3, 4);
    System.out.println("Use reduce() method - to calculate sum of Integers in IntStream");
    Optional<Integer> optionalInteger = streamOfIntegers.reduce((i1, i2) -> i1 + i2);
    System.out.println("sum=" + optionalInteger.get());
  }
}
