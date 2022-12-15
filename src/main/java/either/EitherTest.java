package either;

import org.junit.jupiter.api.Test;

public class EitherTest {

  @Test
  public void eitherTest() {
    Either<Object, Student> either = Either.right(new Student("siege", 20));
    int age = either.map(Student::getAge);
    System.out.println(age);
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
