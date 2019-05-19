package serializable;

public class Person extends Creature {
  private String name;
  private int age;
  private String sex;

  public Person(String name, int age, String sex) {
    System.out.println("Person父类有参构造器," + "name:" + name + ",age:" + age + ",sex:" + sex);
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  /** 如果父类没有实现Serializable接口时， 反序列化需要保证父类有无参构造器 */
  public Person() {
    System.out.println("Person父类无参构造器");
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getSex() {
    return sex;
  }
}
