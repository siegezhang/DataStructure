package java8.reduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Stream;

public class ReduceTest {

  @Test
  public void test1() {
    Stream<Student> stream =
        Stream.of(
            new Student("test1", null, null),
            new Student("test2", 1, 1),
            new Student("test3", 2, 2));

    int sum = stream.map(Student::getCount).filter(Objects::nonNull).reduce(0, Integer::sum);
    System.out.println(sum);
  }

  @Test
  public void test2() {
    Stream<Student> stream =
        Stream.of(
            new Student("test1", null, null),
            new Student("test2", 1, 1),
            new Student("test3", 2, 2));
    Student student = new Student();
    stream
        .filter(e -> e.getCount() != null && e.getCount1() != null)
        .reduce(
            (x, y) -> {
              student.setCount(x.getCount() + y.getCount());
              student.setCount1(x.getCount1() + y.getCount1());
              return student;
            });
    System.out.println(student);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Student {
    private String name;
    private Integer count;
    private Integer count1;
  }
}
