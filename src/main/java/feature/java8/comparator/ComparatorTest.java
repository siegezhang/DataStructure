package feature.java8.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparatorTest {
  @Test
  public void testComparator() {
    List<Student> students =
        Stream.of(
                new Student("hello", 1),
                new Student("world", 2),
                new Student("test", 2),
                new Student("world", null),
                null)
            .sorted(
                Comparator.nullsLast(
                    Comparator.comparing(
                        Student::getSeqNo, Comparator.nullsLast(Comparator.naturalOrder()))))
            .collect(Collectors.toList());
    System.out.println(students);
  }

  @Test
  public void testComparator1() {
    List<Student> students =
        Stream.of(
                new Student("hello", 1),
                new Student("world", 2),
                new Student("test", 2),
                new Student("world", null),
                null)
            .sorted(
                Comparator.nullsFirst(
                    Comparator.comparing(
                            Student::getSeqNo, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .reversed()))
            .collect(Collectors.toList());
    System.out.println(students);
  }

  @Data
  @AllArgsConstructor
  public static class Student {
    private String name;
    private Integer seqNo;
  }
}
