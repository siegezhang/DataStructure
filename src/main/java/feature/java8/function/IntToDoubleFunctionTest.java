package feature.java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntToDoubleFunction;

public class IntToDoubleFunctionTest {
  @Test
  public void test() {
    // Declare IntToDoubleFunction
    IntToDoubleFunction func = a -> 3.12 * a;

    // Apply the function to get the result as double
    // using applyAsDouble()
    System.out.println(func.applyAsDouble(2));
  }
}
