package stream;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class SupplierTest {
  /** Supplier可以用来延迟执行 */
  @Test
  public void test() {
    Supplier<Double> doubleSupplier1 = () -> Math.random();
    DoubleSupplier doubleSupplier2 = Math::random;
  }

  @Test
  public void test1() {
    List<String> collection = null;
    System.out.println(
        String.join(",", Optional.ofNullable(collection).orElse(Collections.emptyList())));
  }
}
