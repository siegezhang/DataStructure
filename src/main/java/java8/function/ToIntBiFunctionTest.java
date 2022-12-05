package java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.ToIntBiFunction;

public class ToIntBiFunctionTest {
  @Test
  public void test() {
    ToIntBiFunction<Integer, String> ob = (a, b) -> a + Integer.parseInt(b) * 3;
    // Using applyAsInt() method
    System.out.println(ob.applyAsInt(3, "10"));
  }
}
