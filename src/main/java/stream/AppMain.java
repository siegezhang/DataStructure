package stream;

import java.util.List;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.Test;

public class AppMain {
  @Test
  public void method1() {
    BiPredicate<String, Integer> filterByLength = (str1, length) -> str1.length() >= length;
    boolean isEqual = filterByLength.test("adam", 5);
    System.out.println(isEqual);
    boolean isEqual1 = filterByLength.test("geek", 4);
    System.out.println(isEqual1);
  }

  @Test
  public void method2() {
    List<Student1> students = Student1.create();
    BiPredicate<Student1, String> filterByDepartment =
        (student, department) -> student.getDepartment().equals(department);
    for (Student1 student : students) {
      boolean result = filterByDepartment.test(student, "medical");
      if (result) System.out.println(student);
    }
  }

  @Test
  public void method3() {
    List<Student1> students = Student1.create();
    BiPredicate<Student1, String> namePrefixFilter =
        (student, prefix) -> student.getName().startsWith(prefix);
    BiPredicate<Student1, String> nameSuffixFilter =
        (student, suffix) -> student.getName().endsWith(suffix);
    for (Student1 student : students) {
      boolean result = namePrefixFilter.and(nameSuffixFilter).test(student, "a");
      if (result) System.out.println(student);
    }
  }
}
