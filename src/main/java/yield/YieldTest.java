package yield;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class YieldTest {
  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3);
    Seq<Integer> seq = list::forEach;

    // Seq<Integer> seq = c -> list.forEach(c);
    //      Seq<Integer> seq = c -> {
    //          for (Integer i : list) {
    //              c.accept(i);
    //          }
    //      };

    //      Seq<Integer> seq = c -> {
    //          c.accept(1);
    //          c.accept(2);
    //          c.accept(3);
    //      };
    System.out.println(underscoreToCamel("Test_my_meThod"));
  }

  static String underscoreToCamel(String str) {
    // 利用生成器构造一个方法的流
    Seq<UnaryOperator<String>> seq =
        c -> {
          // yield第一个小写函数
          c.accept(String::toLowerCase);
          // 这里IDEA会告警，提示死循环风险，无视即可
          while (true) {
            // 按需yield首字母大写函数
            c.accept(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
          }
        };
    List<String> split = Arrays.asList(str.split("_"));
    // 这里的zip和join都在上文给出了实现
    return seq.zip(split, (f, sub) -> f.apply(sub)).join("");
  }
}
