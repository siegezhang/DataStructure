package cglib;

/** 委托类不能是final，同时被委托的方法也不能是final 如果是final类，则直接报错，如果是final方法，则不进行代理，直接执行该方法 */
public class HelloServiceImpl {
  public void sayHello() {
    System.out.println("Hello,World");
  }
}
