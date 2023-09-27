package feature.java18;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Data
public class OptionalTest {

  private String name;

  @Test
  public void testOptional() {
    Optional<String> optional = Optional.ofNullable("hello");
    optional.map(String::toUpperCase).ifPresent(System.out::println);
    optional.flatMap(e -> Optional.ofNullable(e.toUpperCase())).ifPresent(System.out::println);
  }

  /** 非lambda写法 */
  @Test
  public void test1() {
    OptionalTest test = new OptionalTest();
    test.setName("名字");
    // 使用map方法
    String s1 =
        Optional.of(test)
            .map(
                new Function<OptionalTest, String>() {
                  @Override
                  public String apply(OptionalTest test) {
                    return test.getName();
                  }
                })
            .orElse("none");
    System.out.println("map:" + s1);
    // 使用flatMap方法
    String s2 =
        Optional.of(test)
            .flatMap(
                new Function<OptionalTest, Optional<String>>() {
                  @Override
                  public Optional<String> apply(OptionalTest test) {
                    return Optional.ofNullable(test.getName());
                  }
                })
            .orElse("none");
    System.out.println("flatMap:" + s2);
  }

  @Test
  public void test2() {
    OptionalTest test = new OptionalTest();
    test.setName("名字");
    // 使用map方法
    String s1 = Optional.of(test).map(OptionalTest::getName).orElse("none");
    System.out.println("map:" + s1);
    // 使用flatMap方法
    String s2 =
        Optional.of(test).flatMap(test12 -> Optional.ofNullable(test12.getName())).orElse("none");
    System.out.println("flatMap:" + s2);
  }

  @Test
  public void test3() {
    Stream.of(new Student("hello"), new Student("world"))
        .map(
            e -> {
              e.setName(e.getName().toUpperCase());
              if (e.getName().startsWith("H")) e.setName(e.getName() + "11");
              return e;
            })
        .forEach(System.out::println);
  }

  @Data
  @AllArgsConstructor
  public static class Student {
    private String name;
  }
}
