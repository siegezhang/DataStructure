package java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class ConvertArrayListOfStringToStreamsExample {
  @Test
  public void test() {
    List<String> listOfStrings = new ArrayList<>();
    listOfStrings.add("a");
    listOfStrings.add("b");

    System.out.println("2. Convert list (ArrayList) of String to stream");
    Stream<String> stringStream = listOfStrings.stream();
    stringStream.forEach(System.out::println);
  }
}
