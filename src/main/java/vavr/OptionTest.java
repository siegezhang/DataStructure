package vavr;

import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class OptionTest {
  @Test
  public void test1() {
    // 通过 of 工厂方法构造
    Assert.assertTrue(Option.of(null) instanceof Option.None);
    Assert.assertTrue(Option.of(1) instanceof Option.Some);

    // 通过 none 或 some 构造
    Assert.assertTrue(Option.none() instanceof Option.Some);
    Assert.assertTrue(Option.some(1) instanceof Option.Some);
  }

  @Test
  public void testWithJavaOptional() {
    // Java Optional
    var result = Optional.of("hello").map(str -> (String) null).orElseGet(() -> "world");

    // result = "world"
    Assert.assertEquals("world", result);
  }

  @Test
  public void testWithVavrOption() {
    // Vavr Option
    var result = Option.of("hello").map(str -> (String) null).getOrElse(() -> "world");

    // result = null
    Assert.assertNull(result);
  }

  @Test
  public void testVavrOption() {
    // option 直接转为 List
    List<String> result = Option.of("vavr hello world").map(String::toUpperCase).toJavaList();
    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
    Assert.assertEquals("VAVR HELLO WORLD", result.iterator().next());

    // exists(Function)
    boolean exists = Option.of("ok").exists(str -> str.equals("ok"));
    Assert.assertTrue(exists);

    // contains
    boolean contains = Option.of("ok").contains("ok");
    Assert.assertTrue(contains);
  }

  @Test
  public void testToOptional() {
    Option.of("toJava").toJavaOptional();
    Option.ofOptional(Optional.empty());
  }
}
