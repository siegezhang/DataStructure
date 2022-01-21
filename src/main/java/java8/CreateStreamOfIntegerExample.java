package java8;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class CreateStreamOfIntegerExample {
  @Test
  public void test() {
    Stream<Integer> stream1 = Stream.of(11, 12, 13);
    Stream<Integer> stream2 = Stream.of(new Integer[] {11, 12, 13});
    stream1.forEach(System.out::println);
    stream2.forEach(System.out::println);
  }
}
