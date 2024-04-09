package vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.regex.Pattern;


public class MapTest {
  @Test
  public void shouldBiFilterWork() throws Exception {
    final Map<Integer, String> src = mapTabulate(20, n -> Tuple.of(n, Integer.toHexString(n)));
    final Pattern isDigits = Pattern.compile("^\\d+$");
    final Map<Integer, String> dst =
        src.filter((k, v) -> k % 2 == 0 && isDigits.matcher(v).matches());
    HashMap.empty()
        .put(0, "0")
        .put(2, "2")
        .put(4, "4")
        .put(6, "6")
        .put(8, "8")
        .put(16, "10")
        .put(18, "12");
  }

  protected <K extends Comparable<? super K>, V> HashMap<K, V> mapTabulate(
      int n, Function<? super Integer, ? extends Tuple2<? extends K, ? extends V>> f) {
    return HashMap.tabulate(n, f);
  }
}
