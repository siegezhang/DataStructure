package invokedynamic.dynamicproxy;

import org.junit.jupiter.api.Test;

public class DynamicProxyTest {
  @Test
  public void test() {
    BeProxyInterface beProxyInterface = new BeProxyInterfaceImpl();
    BeProxyInterface proxy = (BeProxyInterface) new ProxyHandler().bind(beProxyInterface);
    proxy.testMethod();
  }
}
