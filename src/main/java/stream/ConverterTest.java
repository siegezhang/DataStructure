package stream;

import org.junit.jupiter.api.Test;

/** 了解方法引用的使用 */
public class ConverterTest {
  @Test
  public void test1() {
    Converter converter1 = from -> Integer.valueOf(from);
    Converter converter2 = Integer::valueOf;

    Converter converter3 = from -> "fkit.org".indexOf(from);
    Converter converter4 = "fkit.org"::indexOf;
  }
}
