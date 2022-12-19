package guava;

import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class GuavaTest {
  @Test
  public void testTable() {
    Table<String, String, Integer> table = HashBasedTable.create();
    // 存放元素
    table.put("Hydra", "Jan", 20);
    table.put("Hydra", "Feb", 28);

    table.put("Trunks", "Jan", 28);
    table.put("Trunks", "Feb", 16);

    // 取出元素
    Integer dayCount = table.get("Hydra", "Feb");
    // rowKey或columnKey的集合
    Set<String> rowKeys = table.rowKeySet();
    Set<String> columnKeys = table.columnKeySet();
    // value集合
    Collection<Integer> values = table.values();
    for (String key : table.rowKeySet()) {
      Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
      int total = 0;
      for (Map.Entry<String, Integer> row : rows) {
        total += row.getValue();
      }
      System.out.println(key + ": " + total);
    }

    Table<String, String, Integer> table2 = Tables.transpose(table);
    Set<Table.Cell<String, String, Integer>> cells = table2.cellSet();
    cells.forEach(
        cell ->
            System.out.println(
                cell.getRowKey() + "," + cell.getColumnKey() + ":" + cell.getValue()));

    // 转为嵌套的Map
    Map<String, Map<String, Integer>> rowMap = table.rowMap();
    Map<String, Map<String, Integer>> columnMap = table.columnMap();
  }

  /** HashBiMap需要保证value唯一 */
  @Test
  public void testBiMap() {
    HashBiMap<String, String> biMap = HashBiMap.create();
    biMap.put("Hydra", "Programmer");
    biMap.put("Tony", "IronMan");
    biMap.put("Thanos", "Titan");
    // 使用key获取value
    System.out.println(biMap.get("Tony"));

    BiMap<String, String> inverse = biMap.inverse();
    // 使用value获取key
    System.out.println(inverse.get("Titan"));
  }

  @Test
  public void testMultimap() {
    Multimap<String, Integer> multimap = ArrayListMultimap.create();
    multimap.put("day", 1);
    multimap.put("day", 2);
    multimap.put("day", 8);
    multimap.put("month", 3);
  }

  @Test
  public void testRangeMap() {
    RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
    rangeMap.put(Range.closedOpen(0, 60), "fail");
    rangeMap.put(Range.closed(60, 90), "satisfactory");
    rangeMap.put(Range.openClosed(90, 100), "excellent");

    System.out.println(rangeMap.get(59));
    System.out.println(rangeMap.get(60));
    System.out.println(rangeMap.get(90));
    System.out.println(rangeMap.get(91));

    rangeMap.remove(Range.closed(70, 80));
    System.out.println(rangeMap.get(75));
  }

  @Test
  public void testClassToInstanceMap() {
    ClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
    User user = new User("Hydra", 18);
    Dept dept = new Dept("develop", 200);

    instanceMap.putInstance(User.class, user);
    instanceMap.putInstance(Dept.class, dept);
  }

  private record User(String name, int age) {}

  private record Dept(String dep, int i) {}
}
