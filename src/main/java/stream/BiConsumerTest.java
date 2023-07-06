package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import org.junit.jupiter.api.Test;

public class BiConsumerTest {
  @Test
  public void test1() {
    BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
    addTwo.accept(1, 2);
  }

  @Test
  public void test2() {
    addTwo(1, 2, (x, y) -> System.out.println(x + y));
    addTwo("Node", ".js", (x, y) -> System.out.println(x + y));
  }

  @Test
  public void test3() {
    math(1, 1, (x, y) -> System.out.println(x + y));
    math(1, 1, (x, y) -> System.out.println(x - y));
    math(1, 1, (x, y) -> System.out.println(x * y));
    math(1, 1, (x, y) -> System.out.println(x / y));
  }

  @Test
  public void test4() {
    List<Integer> lista = List.of(2, 1, 3);

    List<Integer> listb = List.of(2, 1, 2);

    BiConsumer<List<Integer>, List<Integer>> equals =
        (list1, list2) -> {
          if (list1.size() != list2.size()) {
            System.out.println("False");
          } else {
            for (int i = 0; i < list1.size(); i++)
              if (list1.get(i) != list2.get(i)) {
                System.out.println("False");
                return;
              }
            System.out.println("True");
          }
        };
    equals.accept(lista, listb);
  }

  @Test
  public void test5() {
    BiConsumer<Integer, Integer> c1 = (a, b) -> System.out.println(a + b);
    c1.accept(10, 20);
    BiConsumer<Integer, Integer> c3 = (a, b) -> System.out.println(a * b);
    c1.andThen(c3).accept(5, 10);
    c1.accept(5, 10);
    c3.accept(5, 10);
  }

  @Test
  public void test6() {
    List<BiConsumer<Integer, Integer>> allConsumers = new ArrayList<>();
    allConsumers.add((a, b) -> System.out.println(a + b));
    allConsumers.add((a, b) -> System.out.println(a - b));
    allConsumers.add((a, b) -> System.out.println(a * b));
    allConsumers.add((a, b) -> System.out.println(a / b));

    BiConsumer<Integer, Integer> consumer =
        allConsumers.stream().reduce(BiConsumer::andThen).orElse(noOpConsumer());

    BiConsumer<Integer, Integer> consumer1 =
        allConsumers.stream().reduce((v1, v2) -> {}, BiConsumer::andThen, BiConsumer::andThen);

    consumer.accept(1, 1);
    consumer1.accept(1, 1);
  }

  @Test
  public void test7() {
    Map<Integer, String> m =
        Map.of(1, "Peter", 2, "Mike", 3, "John", 4, "Mike", 5, "Peter", 6, "Anand", 7, "Peter");
    BiConsumer<Integer, String> f =
        (key, value) -> System.out.println("[Key=" + key + ", " + value + ")]");
    m.forEach(f);
  }

  static <T> void addTwo(T a1, T a2, BiConsumer<T, T> c) {
    c.accept(a1, a2);
  }

  static <Integer> void math(Integer a1, Integer a2, BiConsumer<Integer, Integer> c) {
    c.accept(a1, a2);
  }

  <T, U> BiConsumer<T, U> noOpConsumer() {
    return (value1, value2) -> {
      /* do nothing */
    };
  }
}
