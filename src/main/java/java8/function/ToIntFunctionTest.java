package java8.function;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;

public class ToIntFunctionTest {
  @Test
  public void test() {
    final ArrayList<Person> persons =
        new ArrayList<>(
            Arrays.asList(
                new Person("Ringo", "Starr"),
                new Person("John", "Lennon"),
                new Person("Paul", "Mccartney"),
                new Person("George", "Harrison")));
    final OptionalDouble average =
        persons.stream().filter(p -> p.age >= 40).mapToInt(p -> p.age).average();
    average.ifPresent(System.out::println);
  }

  @Data
  private static class Person {
    private final String name, lastName;
    private final int age;

    public Person(String name, String lastName) {
      this.name = name;
      this.lastName = lastName;
      Random random = new Random();
      this.age = random.nextInt(100);
    }
  }
}
