package java8.default_method;

public interface IFoo {
  default void bar(int i) {
    System.out.println("IFoo.bar(int)");
  }
}
