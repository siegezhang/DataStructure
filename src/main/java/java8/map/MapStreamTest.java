package java8.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapStreamTest {
  @Test
  public void test() {
    Map<String, Integer> imap = new HashMap<>();
    // value的值为null时会报错
    // imap.put("one", null);
    imap.put("one", 1);
    imap.put("two", 2);
    Map<String, Integer> newMap =
        imap.entrySet().stream()
            .collect(
                Collectors.toMap(
                    entry -> StringUtils.capitalize(entry.getKey()), Map.Entry::getValue));
    System.out.println(newMap);
  }

  @Test
  public void test2() {
    Map<String, Integer> imap = new HashMap<>();
    imap.put("one", null);
    imap.put("two", 2);
    Map<String, Integer> newMap =
        imap.entrySet().stream()
            .collect(
                HashMap::new,
                (e, v) -> e.put(StringUtils.capitalize(v.getKey()), v.getValue()),
                HashMap::putAll);
    System.out.println(newMap);
  }

  @Test
  public void test3() {
    List<Map<String, Integer>> list = new ArrayList<>();
    List<Map<String, Integer>> newList = new ArrayList<>();
    Map<String, Integer> imap = new HashMap<>();
    imap.put("one", null);
    imap.put("two", 2);
    list.add(imap);

    list.forEach(
        e -> {
          e =
              e.entrySet().stream()
                  .collect(
                      HashMap::new,
                      (m, v) -> m.put(StringUtils.capitalize(v.getKey()), v.getValue()),
                      HashMap::putAll);
          newList.add(e);
        });
    System.out.println(newList);
  }

  @Test
  public void test4() {
    String[] words = new String[] {"Hello", "World"};
    List<String> a =
        Arrays.stream(words)
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .toList();
    a.forEach(System.out::print);
  }

  /** 测试map后为null的情况 */
  @Test
  public void test5() {
    List<String> names =
        Stream.of(new Student(null, 1), new Student("world", 2)).map(Student::getName).toList();
    System.out.println(names);
  }

  /** 测试map后toSet的情况 */
  @Test
  public void test6() {
    Set<String> names =
        Stream.of(new Student(null, 5), new Student(null, 5), new Student("world", 8))
            .map(Student::getName)
            .collect(Collectors.toSet());
    System.out.println(names);
  }

  @Test
  public void test7() {
    Map<String, Student> map =
        Stream.of(new Student("world", 15), new Student("hello", 16))
            .collect(Collectors.toMap(Student::getName, it -> it));
    map.entrySet().stream()
        .map(
            e -> {
              System.out.println(e);
              return e;
            })
        .toList();
    System.out.println(map);
  }

  /** 测试空集合的情况 */
  @Test
  public void test8() {
    List<Student> students = new ArrayList<>();
    String names = students.stream().map(Student::getName).collect(Collectors.joining());
    System.out.println(names);
  }

  @Data
  @AllArgsConstructor
  public static class Student {
    private String name;
    private int age;
  }
}
