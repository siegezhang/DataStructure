package java8.groupby;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.compress.utils.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class GroupByTest {
  private List<People> people;
  private BiConsumer soutKV;
  private Map result;

  public static <T> Collector<T, ?, List<T>> toSortedList(Comparator<? super T> c) {
    return Collectors.collectingAndThen(
        Collectors.toCollection(() -> new TreeSet<>(c)), ArrayList::new);
  }

  // 收集器 如果获取最小值  Collectors.maxBy(c) -》 Collectors.minBy(c)
  public static <T> Collector<T, ?, T> toSorted(Comparator<? super T> c) {
    Collector<T, ?, T> usersObjectUsersCollector =
        Collectors.collectingAndThen(Collectors.maxBy(c), Optional::get);
    return usersObjectUsersCollector;
  }

  @Before
  public void init() {
    people = People.buildPeopleList();
    soutKV = (k, v) -> System.out.println(k + ":" + v);
  }

  @After
  public void soutResult() {
    result.forEach(soutKV);
  }

  /** 根据性别分组 */
  @Test
  public void groupBySex() {
    result = people.stream().collect(Collectors.groupingBy(People::getSex));
  }

  /** 分别统计不同性别各自的人数 */
  @Test
  public void groupBySexCount() {
    result = people.stream().collect(Collectors.groupingBy(People::getSex, Collectors.counting()));
  }

  /** 分别统计不同性别各自的name列表 */
  @Test
  public void groupBySexName() {
    result =
        people.stream()
            .collect(
                Collectors.groupingBy(
                    People::getSex, Collectors.mapping(People::getName, Collectors.toSet())));
  }

  @Test
  public void groupByGroupingByObj() {
    result =
        people.stream()
            .collect(
                Collectors.groupingBy(
                    it -> {
                      return new GroupingByObj();
                    },
                    Collectors.mapping(People::getName, Collectors.toSet())));
  }

  @Test
  public void testGroupByAndOrderBy() {
    List<OrderMapVo> list = new ArrayList<>();
    // 填充数据...
    // 排序
    TreeMap<String, List<OrderMapVo>> dayMap =
        list.stream()
            .collect(
                Collectors.groupingBy(
                    OrderMapVo::getLaunchDate,
                    TreeMap::new,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        sub ->
                            sub.stream()
                                .sorted(
                                    Comparator.comparing(
                                            OrderMapVo::getSexCode,
                                            Comparator.nullsLast(Comparator.naturalOrder()))
                                        .thenComparing(
                                            OrderMapVo::getCategoryCode,
                                            Comparator.nullsLast(Comparator.naturalOrder())))
                                .collect(Collectors.toList()))));
  }

  @Test
  public void testSorted1() {
    List<Users> list = new ArrayList<>();
    Map<String, List<Users>> sortUsers =
        list.stream()
            .collect(
                Collectors.groupingBy(
                    Users::getName,
                    GroupByTest.toSortedList(Comparator.comparing(Users::getAge).reversed())));
  }

  @Test
  public void testSorted2() {
    List<Users> list = new ArrayList<>();
    Map<String, Users> sortUsersMax =
        list.stream()
            .collect(
                Collectors.groupingBy(
                    Users::getName, GroupByTest.toSorted(Comparator.comparing(Users::getAge))));
  }

  @Test
  public void testSorted3() {
    List<String> list = Arrays.asList("2018-09-04", "2018-09-06", "2018-09-17");
    list.sort(Comparator.comparing(String::valueOf).reversed());
    System.out.println(list);
  }

  @Test
  public void testSorted4() {
    List<Users> list = new ArrayList<>();
    Map<String, List<Users>> sortUsers =
        list.stream()
            .collect(
                Collectors.groupingBy(
                    Users::getName,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        (l ->
                            l.stream()
                                .sorted(Comparator.comparing(Users::getAge))
                                .collect(Collectors.toList())))));
  }

  @Test
  public void testPartitioningBy() {
    Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Map<Boolean, List<Integer>> map = s.collect(Collectors.partitioningBy(num -> num > 3));
    System.out.println("Elements in stream partitioned by less than equal to 3: \n" + map);
  }

  @Test
  public void testEmptyList() {
    List<String> list =
        Lists.<String>newArrayList().stream().map(String::toUpperCase).collect(Collectors.toList());
    Map<String, String> map =
        Lists.<String>newArrayList().stream()
            .map(String::toUpperCase)
            .collect(Collectors.toMap(String::valueOf, it -> it));
    System.out.println(list);
    System.out.println(map);
  }

  public static class GroupingByObj {}

  @Data
  @Accessors(chain = true)
  public class OrderMapVo {
    private String launchDate;
    private String sexCode;
    private String categoryCode;
  }

  @Data
  @Accessors(chain = true)
  public class Users {
    private Integer age;
    private String name;
    private Integer sex;
  }
}
