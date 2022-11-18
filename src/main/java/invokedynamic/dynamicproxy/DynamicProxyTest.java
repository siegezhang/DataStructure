package invokedynamic.dynamicproxy;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

  // 它的作用是帮我们把JDK动态生成的proxy class 的字节码保存到硬盘中,注意：此处是jdk11对应的参数，不同版本可能有所不同
  @BeforeAll
  public static void setVmArg() {
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
  }

  @Test
  public void testProxy() {
    BeProxyInterface beProxyInterface = new BeProxyInterfaceImpl();
    BeProxyInterface proxy = (BeProxyInterface) new ProxyHandler().bind(beProxyInterface);
    proxy.testMethod();
  }

  @Test
  public void testProxy1()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    Class<?> proxyClass =
        Proxy.getProxyClass(DynamicProxyTest.class.getClassLoader(), BeProxyInterface.class);
    Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
    BeProxyInterface proxy =
        (BeProxyInterface) cons.newInstance(new ProxyHandler(new BeProxyInterfaceImpl()));
    proxy.testMethod();
  }
}
