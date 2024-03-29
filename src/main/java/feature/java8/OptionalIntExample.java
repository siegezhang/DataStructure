package feature.java8;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

/** Created by siege on 2017/8/30. */
public class OptionalIntExample {
  @Test
  public void test() {
    OptionalInt optionalInt = OptionalInt.of(5);
    System.out.println("optionalInt.isPresent() =" + optionalInt.isPresent());
    System.out.println("optionalInt.get() =" + optionalInt.getAsInt());
    System.out.println("optionalInt.orElse =" + optionalInt.orElse(6));
    System.out.print("\n1 - optionalInt.ifPresent  ");
    optionalInt.ifPresent(System.out::print);
    System.out.print("\n2 - optionalInt.ifPresent  ");
    optionalInt.ifPresent(d -> System.out.println("value=" + d));
  }
}
