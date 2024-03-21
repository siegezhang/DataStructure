package feature.java22;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Java22Test {
  @Test
  public void test1() {
    String string = "the red brown fox jumps over the lazy dog";
    String[] parts = string.splitWithDelimiters(" ", 5);
    System.out.println(Arrays.stream(parts).collect(Collectors.joining("', '", "'", "'")));
  }
}
