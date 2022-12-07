package java8.function;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
  @Test
  public void test() {
    // Consumer to display a number
    Consumer<Integer> display = a -> System.out.println(a);
    // Implement display using accept()
    display.accept(10);
    // Consumer to multiply 2 to every integer of a list
    Consumer<List<Integer>> modify = list -> list.replaceAll(integer -> 2 * integer);
    // Consumer to display a list of numbers
    Consumer<List<Integer>> dispList =
        list -> list.stream().forEach(a -> System.out.print(a + " "));
    List<Integer> list = List.of(2, 1, 3);
    // Implement modify using accept()
    modify.accept(list);
    // Implement dispList using accept()
    dispList.accept(list);
  }
}
