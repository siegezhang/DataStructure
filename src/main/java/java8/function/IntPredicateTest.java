package java8.function;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class IntPredicateTest {
  @Test
  public void test() {
    IntStream stream = IntStream.range(1, 10);
    List<Integer> oddNumbers = stream.filter(argument -> argument % 2 == 1).boxed().toList();
  }

  @Test
  public void test1() {
    IntPredicate i = (x) -> x < 0;
    IntPredicate j = (x) -> x == 0;
    System.out.println(i.and(j).test(123));
  }
}
