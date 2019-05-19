package invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Test {
  class GrandFather {
    public void thinking() {
      System.out.println("i am grandfather");
    }
  }

  class Father extends GrandFather {
   @Override
   public  void thinking() {
      System.out.println("i am father");
    }
  }

  class Son extends Father {
    @Override
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
