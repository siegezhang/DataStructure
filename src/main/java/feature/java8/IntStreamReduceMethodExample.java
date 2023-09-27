package feature.java8;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/** Created by siege on 2017/8/30. */
public class IntStreamReduceMethodExample {
  @Test
  public void test() {
    System.out.println("Create IntStream");
    IntStream streamOfIntegers = IntStream.of(1, 2, 3, 4);
    System.out.println("Use reduce() method - to calculate sum of Integers in IntStream");
    OptionalInt optionalInt = streamOfIntegers.reduce((i1, i2) -> i1 + i2);
    System.out.println("sum=" + optionalInt.getAsInt());
  }
}
