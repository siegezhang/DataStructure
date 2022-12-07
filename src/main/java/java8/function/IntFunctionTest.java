package java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;

public class IntFunctionTest {
  @Test
  public void test() {
    IntFunction<Double> ob = a -> a / 2.0;

    // Using apply() method
    System.out.println(ob.apply(3));
  }
}
