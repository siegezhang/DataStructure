package feature.java8.reduce;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Person {
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
}

@Getter
@Setter
class Result {
  private String name;
  private int age;

  public Result() {}
}

public class Main {
  public static void main(String[] args) {
    List<Person> people =
        Arrays.asList(
            new Person("John", 30),
            new Person("Mary", 25),
            new Person("Peter", 40),
            new Person("Susan", 35));

    Result person1 =
        people.stream()
            .reduce(
                new Result(),
                (result, person) -> {
                  result.setName(result.getName() + person.getName());
                  result.setAge(result.getAge() + person.getAge());

                  return result;
                },
                (result1, result2) -> {
                  result1.setName(result1.getName() + result2.getName());
                  result1.setAge(result1.getAge() + result2.getAge());
                  return result1;
                });

    System.out.println(person1); // Result(name=Peter, age=40)
  }
}
