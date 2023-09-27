package feature.java9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class JDK9Test {
  @Test
  public void test1() {
    List<String> immutableList = List.of();
    immutableList = List.of("one", "two", "three");

    Map emptyImmutableMap = Map.of();
    Map nonemptyImmutableMap = Map.of(1, "one", 2, "two", 3, "three");
  }

  @Test
  public void test2() {
    List<String> immutableList = List.of();
    immutableList = List.of("one", "two", "three");

    Map emptyImmutableMap = Map.of();
    Map nonemptyImmutableMap = Map.of(1, "one", 2, "two", 3, "three");
  }
}
