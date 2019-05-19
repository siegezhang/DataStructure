package serializable;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestSerivalizable {
  @Test
  public void testSerivalizable() {
    FileOutputStream fos;
    ObjectOutputStream oos;
    Student student = new Student(78f, 2);
    try {
      fos = new FileOutputStream("F:\\test\\student.out");
      oos = new ObjectOutputStream(fos);
      oos.writeObject(student);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDeSerivalizable() {
    FileInputStream fio;
    ObjectInputStream ois;
    try {
      fio = new FileInputStream("F:\\test\\student.out");
      ois = new ObjectInputStream(fio);
      Student s = (Student) ois.readObject();
      System.out.println(
          "name:"
              + s.getName()
              + ",age:"
              + s.getAge()
              + ",sex:"
              + s.getSex()
              + ",score:"
              + s.getScore()
              + ",grade:"
              + s.getGrade());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 反序列化会忽略static,transient 字段的属性，在此处输出count=3时因为该类已经被加载进来了, 在创建的时候已经为3了， 如果我们另写一个方法读取该序列化文件，则为0
   */
  @Test
  public void testMore() {
    Person1 person1 = new Person1("siege1", 20);
    Person1 person2 = new Person1("siege2", 21);
    Person1 person3 = new Person1("siege3", 22);
    try {
      FileOutputStream fos = new FileOutputStream("F:\\test\\person.out");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
      objectOutputStream.writeObject(person1);
      objectOutputStream.writeObject(person2);
      objectOutputStream.writeObject(person3);
      objectOutputStream.close();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }

    try {
      FileInputStream fin = new FileInputStream("F:\\test\\person.out");
      ObjectInputStream objectInputStream = new ObjectInputStream(fin);
      try {
        Person1 p1 = (Person1) objectInputStream.readObject();
        Person1 p2 = (Person1) objectInputStream.readObject();
        Person1 p3 = (Person1) objectInputStream.readObject();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
