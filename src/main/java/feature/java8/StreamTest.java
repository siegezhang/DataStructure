package feature.java8;

import com.google.common.collect.Lists;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/** Stream的一些高级用法 */
public class StreamTest {
  @Test
  public void test() {
    List<String> listOfStrings = new ArrayList<>();
    listOfStrings.add("a");
    listOfStrings.add("b");
    List<String> asList =
        listOfStrings.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    System.out.println(asList);
  }

  @Test
  public void test1() {
    List<Integer> listOfIntegers = new ArrayList<>();
    listOfIntegers.add(11);
    listOfIntegers.add(12);

    List<Integer> asList =
        listOfIntegers.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    System.out.println(asList);
  }

  @Test
  public void test2() {
    List<String> stringList = new ArrayList<>();
    stringList.add("ank");
    stringList.add("sam");
    stringList.add("az");
    stringList.add("neh");
    stringList.add("ad");

    Stream<String> stream = stringList.stream();
    System.out.println(
        "\n1.Use stream for filtering, sorting and display(in sorted manner - DESCENDING order)");
    stream
        .filter(s -> s.startsWith(s))
        .sorted((a, b) -> b.compareTo(a))
        .forEach(System.out::println);

    stream = stringList.stream();
    System.out.println(
        "\n2.Use stream for filtering, sorting and display(in sorted manner - DESCENDING order)");
    stream
        .filter(s -> s.startsWith("a"))
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
  }

  @Test
  public void test3() {
    System.out.println("Create DoubleStream");
    DoubleStream streamOfDoubles = DoubleStream.of(1.1, 2.2, 3.1);
    System.out.println("Use reduce() method - to calculate sum of Double in DoubleStream");
    OptionalDouble optionalDouble = streamOfDoubles.reduce((i1, i2) -> i1 + i2);
    System.out.println("sum=" + optionalDouble.getAsDouble());
  }

  @Test
  public void test4() {
    System.out.println("Create DoubleStream");
    DoubleStream streamOfDoubles = DoubleStream.of(1.1, 2.2, 3.1);
    System.out.println("Use reduce() method - to calculate sum of Double in DoubleStream");
    OptionalDouble optionalDouble = streamOfDoubles.reduce((i1, i2) -> i1 + i2);
    System.out.println("sum=" + optionalDouble.getAsDouble());
  }

  @Test
  public void test5() {
    List<Student> list = new ArrayList<>();
    list.add(new Student());
    list.add(new Student());
    List<Student> a =
        list.stream()
            .map(
                e -> {
                  e.setName("test");
                  return e;
                })
            .filter(e -> StringUtils.equals(e.getName(), "test"))
            .collect(Collectors.toList());
    System.out.println(a);
  }

  @Test
  public void test6() {
    List<Integer> list1 = Lists.<Integer>newArrayList(1, 2, 3, 4);
    List<Integer> list2 = Lists.<Integer>newArrayList(3, 4, 5, 6);
    list1.removeAll(list2);
    System.out.println(list1);
  }

  /** 失败后重试最多5次，使用场景可以在调用外部接口时增加由于网络问题导致的失败的健壮性 */
  @Test
  public void test7() {
    Stream.generate(() -> Math.random() > 0.8 ? "ok" : null)
        .limit(5)
        // .filter(Objects::nonNull)
        .forEach(System.out::println);
    // .findFirst()
    // .ifPresent(System.out::println);
    //
    //    Stream.iterate(0, i -> i + 2).takeWhile(e -> e < 10).forEach(System.out::println);
    //    Stream.iterate(0, i -> i + 2).dropWhile(e -> e < 10).forEach(System.out::println);
    //
    //    Stream.iterate(0, i -> i + 2)
    //        .takeWhile(
    //            e -> {
    //              return e < 10;
    //            })
    //        .forEach(System.out::println);
    //    System.getProperties().entrySet().forEach(System.out::println);
  }

  public static class Student {
    private String name;
    private int age;

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
