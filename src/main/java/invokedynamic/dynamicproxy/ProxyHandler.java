package invokedynamic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
  private BeProxyInterface beProxyInterface;

  public ProxyHandler(BeProxyInterface beProxyInterface) {
    this.beProxyInterface = beProxyInterface;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before invoke");
    method.invoke(beProxyInterface, args);
    System.out.println("after invoke");
    return null;
  }
}
