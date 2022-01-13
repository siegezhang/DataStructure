package java8.groupby;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class GroupByTest {
  private List<People> people;
  private BiConsumer soutKV;
  private Map result;

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

  public static class GroupingByObj {}
}
