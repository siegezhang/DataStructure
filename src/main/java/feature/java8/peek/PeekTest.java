package feature.java8.peek;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** peek是一个中间操作，和map操作不同的在于map可以返回一个不同的类型 */
public class PeekTest {
  /** 注意lambda中的重新赋值问题 */
  @Test
  public void test() {
    Stream<String> stream = Stream.of("hello", "felord.cn");
    //  List<String> strs = stream.peek(System.out::println).collect(Collectors.toList());
    List<String> strings = stream.peek(e -> e = e + "_test").collect(Collectors.toList());
    System.out.println(strings);
  }

  @Test
  public void test1() {
    Stream<Student> stream =
        Stream.of(Student.builder().name("小明").build(), Student.builder().name("小红").build());
    //  List<String> strs = stream.peek(System.out::println).collect(Collectors.toList());
    List<Student> students =
        stream.peek(e -> e.setName(e.getName() + "_test")).collect(Collectors.toList());
    System.out.println(students);
  }

  @Data
  @AllArgsConstructor
  @Builder
  public static class Student {
    private String name;
    private Integer age;
  }
}
