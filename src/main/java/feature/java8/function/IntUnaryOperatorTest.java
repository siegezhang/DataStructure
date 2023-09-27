package feature.java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntUnaryOperator;

public class IntUnaryOperatorTest {
  @Test
  public void test() {
    IntUnaryOperator op = IntUnaryOperator.identity();
    System.out.println(op.applyAsInt(12));

    IntUnaryOperator op1 = a -> 2 * a;
    System.out.println(op1.applyAsInt(12));

  }
}
