package java8.function;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Comparator;
import java.util.function.BinaryOperator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BinaryOperatorTest {
  Student s1;
  Student s2;
  Student s3;
  Student s4;
  Comparator<Student> ageComparator;

  @BeforeAll
  public void before() {
    s1 = new Student("xiaoming", 22);
    s2 = new Student("xiaohong", 23);
    s3 = new Student("xiaofang", 22);
    s4 = new Student("xiaoqiang", 21);
    ageComparator = Comparator.comparing(Student::age);
  }

  @Test
  public void test() {
    BinaryOperator<Integer> binaryOperator = (n, k) -> n + k;
    BinaryOperator<Integer> binaryOperator1 = Integer::sum;
  }

  @Test
  public void testMaxBy() {
    BinaryOperator<Student> binaryOperator = BinaryOperator.maxBy(ageComparator);
    Student olderStudent = binaryOperator.apply(s1, s2);
    System.out.println("年长的学生是：" + olderStudent.name());
  }

  @Test
  public void testMinBy() {
    BinaryOperator<Student> binaryOperator1 = BinaryOperator.minBy(ageComparator);
    Student youngerStudent = binaryOperator1.apply(s3, s4);
    System.out.println("年轻的学生是：" + youngerStudent.name());
  }
}

record Student(String name, int age) {}
