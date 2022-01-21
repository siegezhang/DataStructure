package java8;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/** Created by siege on 2017/8/30. */
public class DoubleStreamReduceMethodExample {
  @Test
  public void test() {
    System.out.println("Create DoubleStream");
    DoubleStream streamOfDoubles = DoubleStream.of(1.1, 2.2, 3.1);
    System.out.println("Use reduce() method - to calculate sum of Double in DoubleStream");
    OptionalDouble optionalDouble = streamOfDoubles.reduce((i1, i2) -> i1 + i2);
    System.out.println("sum=" + optionalDouble.getAsDouble());
  }
}
