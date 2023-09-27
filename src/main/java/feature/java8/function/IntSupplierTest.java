package feature.java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;

public class IntSupplierTest {
  @Test
  public void test() {
    IntSupplier sup = () -> (int) (Math.random() * 10);

    // Get the int value
    // Using getAsInt() method
    System.out.println(sup.getAsInt());
  }
}
