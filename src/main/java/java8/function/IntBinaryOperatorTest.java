package java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntBinaryOperator;

public class IntBinaryOperatorTest {
  @Test
  public void test() {
    IntBinaryOperator binaryOperator =
        (x, y) -> {
          int fact1 = 1;
          for (int i = 2; i <= x; i++) {
            fact1 *= i;
          }
          int fact2 = 1;
          for (int i = 2; i <= y; i++) {
            fact2 *= i;
          }
          return fact1 / fact2;
        };

    System.out.println("5! divided by 7! = " + binaryOperator.applyAsInt(5, 7));
    System.out.println("7! divided by 5! = " + binaryOperator.applyAsInt(7, 5));
  }
}
