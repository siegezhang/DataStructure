package java8.map;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
