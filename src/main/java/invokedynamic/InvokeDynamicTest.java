package invokedynamic;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class InvokeDynamicTest {
  public static void main(String[] args) throws Throwable {
    CallSite cs =
        (CallSite)
            MethodHandles.lookup()
                .findStatic(
                    InvokeDynamicTest.class,
                    "BootstrapMethod",
                    MethodType.fromMethodDescriptorString(
                        "(Ljava/lang/invoke/MethodHandles$Lookup;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/invoke/MethodType;)" +
                                "Ljava/lang/invoke/CallSite;",
                        null))
                .invokeWithArguments(
                    MethodHandles.lookup(),
                    "testMethod",
                    MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
    cs.dynamicInvoker().invokeExact("icyfenix");
  }

  public static void testMethod(String s) {
    System.out.println("hello String：" + s);
  }

  public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt)
      throws Throwable {
    return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
  }
}
