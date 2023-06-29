package yield;

interface TestInterface {
  static void test() {
    System.out.println("TestInterface.test");
  }
}

class TestImpl implements TestInterface {}

public class Test {
  public static void main(String[] args) {
    TestInterface t = new TestImpl();
    TestInterface.test();
  }
}
