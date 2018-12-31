package java8.default_method;

public class Foo implements IFoo {
  public static void main(String[] args) {
    Foo foo = new Foo();
    foo.bar(42);
    IFoo ifoo = foo;
    ifoo.bar(42);
  }

  public void bar(long i) {
    System.out.println("Foo.bar(long)");
  }
}
