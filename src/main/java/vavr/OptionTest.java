package vavr;

import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class OptionTest {
  @Test
  public void test1() {
    // 通过 of 工厂方法构造
    Assertions.assertTrue(Option.of(null) instanceof Option.None);
    Assertions.assertTrue(Option.of(1) instanceof Option.Some);

    // 通过 none 或 some 构造
    Assertions.assertTrue(Option.none() instanceof Option.Some);
    Assertions.assertTrue(Option.some(1) instanceof Option.Some);
  }

  @Test
  public void testWithJavaOptional() {
    // Java Optional
    var result = Optional.of("hello").map(str -> (String) null).orElseGet(() -> "world");

    // result = "world"
    Assertions.assertEquals("world", result);
  }

  @Test
  public void testWithVavrOption() {
    // Vavr Option
    var result = Option.of("hello").map(str -> (String) null).getOrElse(() -> "world");

    // result = null
    Assertions.assertNull(result);
  }

  @Test
  public void testVavrOption() {
    // option 直接转为 List
    List<String> result = Option.of("vavr hello world").map(String::toUpperCase).toJavaList();
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals("VAVR HELLO WORLD", result.iterator().next());

    // exists(Function)
    boolean exists = Option.of("ok").exists(str -> str.equals("ok"));
    Assertions.assertTrue(exists);

    // contains
    boolean contains = Option.of("ok").contains("ok");
    Assertions.assertTrue(contains);
  }

  @Test
  public void testToOptional() {
    Option.of("toJava").toJavaOptional();
    Option.ofOptional(Optional.empty());
  }
}
