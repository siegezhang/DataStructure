package stream;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * @author siege
 */
public class BiPredicateTest {

  @Test
  public void test1() {
    List<BiPredicate<String, String>> allPredicates = new ArrayList<>();
    allPredicates.add((str1, str2) -> str1.startsWith("A") && str2.startsWith("A"));
    allPredicates.add((str1, str2) -> str1.contains("d") && str2.contains("d"));
    allPredicates.add((str1, str2) -> str1.length() > 4 && str2.length() > 4);
    List<MyTuple> result =
        Lists.newArrayList(MyTuple.of("Ad1221", "Ad2345"), MyTuple.of("asd", "asd")).stream()
            .filter(
                e ->
                    allPredicates.stream()
                        .reduce((x, y) -> true, BiPredicate::and)
                        .test(e.v1, e.v2))
            .collect(Collectors.toList());
    System.out.println(result);
  }

  @Test
  public void test2() {
    List<BiPredicate<String, String>> allPredicates = new ArrayList<>();
    allPredicates.add((str1, str2) -> str1.startsWith("A") && str2.startsWith("A"));
    allPredicates.add((str1, str2) -> str1.contains("d") && str2.contains("d"));
    allPredicates.add((str1, str2) -> str1.length() > 4 && str2.length() > 4);

    List<MyTuple> result =
        Lists.newArrayList(MyTuple.of("Ad1221", "Ad2345"), MyTuple.of("asd", "asd")).stream()
            .filter(
                e ->
                    allPredicates.stream()
                        .reduce((x1, x2) -> false, BiPredicate::or)
                        .test(e.v1, e.v2))
            .collect(Collectors.toList());
    System.out.println(result);
  }

  @Test
  public void test3() {
    BiPredicate<String, Integer> biPredicate = (str, num) -> str.length() == num;
    System.out.println(biPredicate.test("hello", 5));
    Predicate<String> predicate = str -> biPredicate.test(str, 5);
    System.out.println(predicate.test("hello"));
  }

  @Getter
  private static class MyTuple {
    private final String v1;
    private final String v2;

    public MyTuple(String v1, String v2) {
      this.v1 = v1;
      this.v2 = v2;
    }

    public static MyTuple of(String v1, String v2) {
      return new MyTuple(v1, v2);
    }

    @Override
    public String toString() {
      return "(v1=" + v1 + ", v2=" + v2 + ")";
    }
  }
}
