package serializable;

import java.io.Serializable;

public class Person1 implements Serializable {
  private static int count;
  private transient int num = 3;
  private transient String description = "hello";
  private String name;
  private int age;

  public Person1(String name, int age) {
    System.out.println("Person1");
    count++;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "name"
        + ":"
        + name
        + ",age:"
        + age
        + ",count:"
        + count
        + ",num:"
        + num
        + ",description:"
        + description;
  }
}
