package either;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class EitherTest {

  public static Either<Exception, Student> readLine(int i) {
    if (new Random().nextInt(1, 100) < 50) {
      return Either.right(new Student("siege", 20));
    }
    return Either.left(new Exception("第" + i + "行出现异常"));
  }

  @Test
  public void eitherTest() {
    Either<Exception, Student> either = Either.right(new Student("siege", 20));
    Either<Exception, Integer> otherEither = either.map(Student::getAge);
    System.out.println(otherEither);
  }

  @Test
  public void eitherTest1() {
    List<Either<Exception, Student>> eitherList =
        Stream.iterate(1, i -> i + 1).limit(100).map(EitherTest::readLine).toList();
  }

  static class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}
