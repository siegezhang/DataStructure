package invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
  static class ClassA {
    public void println(String s) {
      System.out.println(s);
    }
  }

  public static void main(String[] args) throws Throwable {
    Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
    /*无论obj最终是哪个实现类，下面这句都能正确调用到println方法*/
    getPrintlnMH(obj).invokeExact("icyfenix");
  }

  private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {

    /*lookup（）方法来自于MethodHandles.lookup，
    这句的作用是在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄
        因为这里调用的是一个虚方法，按照Java语言的规则，方法第一个参数是隐式的，代表该方法的接收者，也即是this指向的对象，
    这个参数以前是放在参数列表中进行传递的，而现在提供了bindTo（）方法来完成这件事情*/
    return MethodHandles.lookup()
        .findVirtual(
            reveiver.getClass(), "println", MethodType.methodType(void.class, String.class))
        .bindTo(reveiver);
  }
}
