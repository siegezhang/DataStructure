package invokedynamic.dynamicproxy;

import org.junit.jupiter.api.Test;

public class DynamicProxyTest {
  @Test
  public void test() {
    BeProxyInterface beProxyInterface = new BeProxyInterfaceImpl();
    ProxyHandler handler = new ProxyHandler(beProxyInterface);
    handler.getProxy().testMethod();
  }
}
