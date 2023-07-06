package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

/** Consumer可以用来作为回调或者监听,例如一个方法接受Consumer参数,处理完数据之后执行Consumer逻辑,这样就相当于回调了 */
public class ConsumerTest {
  @Test
  public void test1() {
    Consumer<String> c = x -> System.out.println(x.toLowerCase());
    Consumer<String> c1 = x -> System.out.println(x.toUpperCase());
    c.andThen(c1).accept("Java2s.com");
  }

  @Test
  public void test2() {
    List<Student> students =
        Arrays.asList(
            new Student(1, 3, "John"), new Student(2, 4, "Jane"), new Student(3, 3, "Jack"));

    Consumer<Student> raiser = e -> e.gpa = e.gpa * 1.1;
    raiseStudents(students, System.out::println);
    raiseStudents(students, raiser.andThen(System.out::println));
  }

  @Test
  public void test3() {
    List<Student> students =
        Arrays.asList(
            new Student(1, 3, "John"), new Student(2, 4, "Jane"), new Student(3, 3, "Jack"));

    List<Consumer<Student>> allConsumers = new ArrayList<>();
    allConsumers.add(e -> e.gpa = e.gpa * 1.1);
    allConsumers.add(System.out::println);

    Consumer<Student> consumer =
        allConsumers.stream().reduce(Consumer::andThen).orElse(noOpConsumer());

    Consumer<Student> consumer1 =
        allConsumers.stream().reduce(t -> {}, Consumer::andThen, Consumer::andThen);

    raiseStudents(students, consumer);
    raiseStudents(students, consumer1);
  }

  private static void raiseStudents(List<Student> employees, Consumer<Student> fx) {
    for (Student e : employees) {
      fx.accept(e);
    }
  }

  <T> Consumer<T> noOpConsumer() {
    return value -> {
      /* do nothing */
    };
  }
}

class Student {
  public int id;
  public double gpa;
  public String name;

  Student(int id, long g, String name) {
    this.id = id;
    this.gpa = g;
    this.name = name;
  }

  @Override
  public String toString() {
    return id + ">" + name + ": " + gpa;
  }
}
