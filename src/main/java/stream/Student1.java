package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student1 {

  private static final int BOUND = 100;
  private static final Random RANDOM = new Random();

  private final int id;
  private final String name;
  private final String department;

  private Student1(int id, String name, String department) {
    this.id = id;
    this.name = name;
    this.department = department;
  }

  public static List<Student1> create() {
    List<Student1> Student1s = new ArrayList<>();
    Student1s.add(new Student1(RANDOM.nextInt(BOUND), "adam", "medical"));
    Student1s.add(new Student1(RANDOM.nextInt(BOUND), "eve", "commerce"));
    Student1s.add(new Student1(RANDOM.nextInt(BOUND), "john", "non-medical"));
    Student1s.add(new Student1(RANDOM.nextInt(BOUND), "asha", "medical"));

    return Student1s;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    return "Student1{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", department='"
        + department
        + '\''
        + '}';
  }
}
