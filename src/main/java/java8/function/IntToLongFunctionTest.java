package java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntToLongFunction;

public class IntToLongFunctionTest {
  @Test
  public void test() {
    // Declare the IntToLongFunction
    IntToLongFunction func = a -> 1000000 * a;

    // Apply the function to get the result as long
    // using applyAsLong()
    System.out.println(func.applyAsLong(2));
  }
}
