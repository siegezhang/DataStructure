package feature.java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class StreamsExample4_ReduceListToStoreInOptional {
  @Test
  public void test() {
    List<String> stringList = new ArrayList<>();
    stringList.add("a");
    stringList.add("b");
    stringList.add("a");

    Stream<String> stream = stringList.stream();
    System.out.println("1.Reduce list to store it in Optional");
    Optional<String> optional = stream.sorted().reduce((s1, s2) -> s1 + "," + s2);
    System.out.println(optional.isPresent());
    System.out.println(optional.get());
  }
}
