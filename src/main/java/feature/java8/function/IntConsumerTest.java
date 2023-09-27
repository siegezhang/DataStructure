package feature.java8.function;

import org.junit.jupiter.api.Test;

import java.util.function.IntConsumer;

public class IntConsumerTest {
  @Test
  public void test() {
    // Create a IntConsumer Instance
    IntConsumer display = a -> System.out.println(a * 10);
    IntConsumer mul = a -> a *= 10;

    // Using andThen() method
    IntConsumer composite = mul.andThen(display);
    composite.accept(3);
  }
}
