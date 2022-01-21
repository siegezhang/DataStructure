package java8;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class CreateStreamOfStringExample {
  @Test
  public void test() {
    Stream<String> stream1 = Stream.of("a", "b");
    Stream<String> stream2 = Stream.of(new String[] {"a", "b"});
    stream1.forEach(System.out::println);
    stream2.forEach(System.out::println);
  }
}
