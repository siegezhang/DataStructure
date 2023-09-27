package feature.java8.default_method;

import org.junit.jupiter.api.Test;

public class Foo implements IFoo {
  @Test
  public void test() {
    Foo foo = new Foo();
    foo.bar(42);
    IFoo ifoo = foo;
    ifoo.bar(42);
  }

  public void bar(long i) {
    System.out.println("Foo.bar(long)");
  }
}
