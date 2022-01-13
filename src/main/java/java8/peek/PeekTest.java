package java8.peek;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** peek是一个中间操作，和map操作不同的在于map可以返回一个不同的类型 */
public class PeekTest {
  @Test
  public void test() {
    Stream<String> stream = Stream.of("hello", "felord.cn");
    List<String> strs = stream.peek(System.out::println).collect(Collectors.toList());
  }
}
