package feature.java8.currying;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import org.junit.jupiter.api.Test;

public class CurryTest {
  @Test
  public void test() {
    IntBinaryOperator simpleAdd = (a, b) -> a + b;
    IntFunction<IntUnaryOperator> curriedAdd = a -> b -> a + b;
    Function<Integer, Function<Integer, Integer>> curriedAdd1 = a -> b -> a + b;

    // Demonstrating simple add:
    System.out.println(simpleAdd.applyAsInt(4, 5));

    // Demonstrating curried add:
    System.out.println(curriedAdd.apply(4).applyAsInt(5));

    // Curried version lets you perform partial application:
    IntUnaryOperator adder5 = curriedAdd.apply(5);
    System.out.println(adder5.applyAsInt(4));
    System.out.println(adder5.applyAsInt(6));
  }
}
