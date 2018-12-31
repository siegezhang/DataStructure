package generic;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
  public  void test()throws Exception {
    List<String> list = null; // 1
    list = new ArrayList<String>(); // 2
    for (int i = 0; i < 10; i++) ;
  }
}
