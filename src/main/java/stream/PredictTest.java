package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * @author siege
 */
public class PredictTest {

  private List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");

  @Test
  public void test1() {
    List<Predicate<String>> allPredicates = new ArrayList<>();
    allPredicates.add(str -> str.startsWith("A"));
    allPredicates.add(str -> str.contains("d"));
    allPredicates.add(str -> str.length() > 4);
    List<String> result =
        names.stream()
            .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
            .collect(Collectors.toList());
    System.out.println(result);
  }

  @Test
  public void test2() {
    List<Predicate<String>> allPredicates = new ArrayList<>();
    allPredicates.add(str -> str.startsWith("A"));
    allPredicates.add(str -> str.contains("d"));
    allPredicates.add(str -> str.length() > 4);

    List<String> result =
        names.stream()
            .filter(allPredicates.stream().reduce(x -> false, Predicate::or))
            .collect(Collectors.toList());
    System.out.println(result);
  }
}
