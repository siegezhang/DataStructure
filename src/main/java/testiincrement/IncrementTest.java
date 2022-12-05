package testiincrement;

import org.junit.jupiter.api.Test;

public class IncrementTest {
  @Test
  public void test1() {
    int[] arr = {1, 2, 3};
    int i = 1;
    arr[i] = i++;
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);
  }

  @Test
  public void test2() {
    int i = 0;
    i = i++;
    System.out.println(i);
  }
}
