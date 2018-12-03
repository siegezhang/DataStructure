package invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

public class Test {
  class GrandFather {
    public void thinking() {
      System.out.println("i am grandfather");
    }
  }

  class Father extends GrandFather {
   public  void thinking() {
      System.out.println("i am father");
    }
  }

  class Son extends Father {
    public void  thinking() {
      try {
        MethodHandle mh =
            MethodHandles.lookup()
                .findVirtual(GrandFather.class, "thinking", MethodType.methodType(void.class))
                .bindTo(new Test().new GrandFather());
        mh.invokeExact();

      } catch (Throwable e) {
          e.printStackTrace();
      }

    }
  }

  public static void main(String[] args) {
    (new Test().new Son()).thinking();
  }
}
