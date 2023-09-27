package feature.java8.map;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

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
            .collect(Collectors.toMap(Student::getName, Function.identity()));
    map.entrySet().stream().peek(System.out::println).toList();
    System.out.println(map);
  }

  /** 测试空集合的情况 */
  @Test
  public void test8() {
    List<Student> students = new ArrayList<>();
    String names = students.stream().map(Student::getName).collect(Collectors.joining());
    System.out.println(names);
  }
  /** 测试空集合的情况 */
  @Test
  public void test9() {
    HashMap<Integer, String> firstMap = new HashMap<>();
    firstMap.put(1, "A");
    firstMap.put(2, "B");
    firstMap.put(3, "C");
    firstMap.put(4, "D");
    HashMap<Integer, String> secondMap = new HashMap<>();
    secondMap.put(4, "F");
    secondMap.put(5, "G");
    secondMap.forEach((key, value) -> firstMap.merge(key, value, (s, str) -> s.concat(str)));
    System.out.println(firstMap);
  }

  @Test
  public void test10() {
    Map<String, BigDecimal> mergedMap =
        getList().stream()
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, BigDecimal::add));
    System.out.println(mergedMap);
  }

  @Test
  public void test11() {
    Map<String, BigDecimal> mergedMap =
        getList().stream()
            .flatMap(map -> map.entrySet().stream())
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getKey,
                    Collectors.reducing(BigDecimal.ZERO, Map.Entry::getValue, BigDecimal::add)));
    System.out.println(mergedMap);
  }

  @NotNull
  private static ArrayList<Map<String, BigDecimal>> getList() {
    return Lists.newArrayList(
        new HashMap<>() {
          {
            put("1", new BigDecimal("0.2"));
            put("2", new BigDecimal("0.2"));
            put("3", new BigDecimal("0.2"));
            put("4", new BigDecimal("0.2"));
          }
        },
        new HashMap<>() {
          {
            put("4", new BigDecimal("0.2"));
            put("5", new BigDecimal("0.2"));
          }
        },
        new HashMap<>() {
          {
            put("4", new BigDecimal("0.2"));
            put("5", new BigDecimal("0.2"));
          }
        });
  }

  @Test
  public void test12() {

    List<Map<String, Double>> list =
        Lists.newArrayList(
            new HashMap<>() {
              {
                put("1", 0.2);
                put("2", 0.2);
                put("3", 0.2);
                put("4", 0.2);
              }
            },
            new HashMap<>() {
              {
                put("4", 0.2);
                put("5", 0.2);
              }
            },
            new HashMap<>() {
              {
                put("4", 0.2);
                put("5", 0.2);
              }
            });

    Map<String, Double> mergedMap =
        list.stream()
            .flatMap(map -> map.entrySet().stream())
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)));

    System.out.println(mergedMap);
  }

  @Test
  public void test13() {
    String cookies = "i=lol;haha=noice;df3=ddtb";
    Map<String, String> map =
        Pattern.compile(";")
            .splitAsStream(StringUtils.isEmpty(cookies) ? StringUtils.EMPTY : cookies)
            .map(str -> str.split("="))
            .collect(
                HashMap::new,
                (m, entry) -> {
                  if (StringUtils.isEmpty(entry[0])) return;
                  m.put(entry[0], entry[1]);
                },
                HashMap::putAll);
    map.entrySet().forEach(System.out::println);
  }

  @Test
  public void test14() {
    String modulesToUpdate = "potato:module1,tomato:module2";
    Stream.of(modulesToUpdate)
        .map(line -> line.split(","))
        .flatMap(Arrays::stream)
        .flatMap(Pattern.compile(":")::splitAsStream)
        .forEach(f -> System.out.println(f));
  }

  /** TODO 思考如何处理null或空的情况 */
  @Test
  public void test15() {
    String cookies = "i=lol;haha=noice;df3=ddtb";
    Map<String, String> map =
        Pattern.compile(";")
            .splitAsStream(StringUtils.isEmpty(cookies) ? cookies : StringUtils.EMPTY)
            .map(str -> str.split("="))
            .collect(
                Collectors.toMap(
                    a -> StringUtils.isEmpty(a[0]) ? StringUtils.EMPTY : a[0],
                    a -> StringUtils.isEmpty(a[1]) ? StringUtils.EMPTY : a[1],
                    (a, b) -> a));
  }

  @Data
  @AllArgsConstructor
  public static class Student {
    private String name;
    private int age;
  }
}
