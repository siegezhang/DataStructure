package invokedynamic.dynamicproxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
  @Test
  public void test() {
    BeProxyInterface beProxyInterface = new BeProxyInterfaceImpl();
    ProxyHandler handler = new ProxyHandler(beProxyInterface);
    BeProxyInterface proxy =
        (BeProxyInterface)
            Proxy.newProxyInstance(
                handler.getClass().getClassLoader(), new Class[] {BeProxyInterface.class}, handler);
    proxy.testMethod();
  }
}
