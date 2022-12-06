package java8.function;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class IntPredicateTest {
  @Test
  public void test() {
    IntStream stream = IntStream.range(1, 10);
    List<Integer> oddNumbers = stream.filter(argument -> argument % 2 == 1).boxed().toList();
  }
}
