package invokedynamic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
  private BeProxyInterface beProxyInterface;

  public ProxyHandler(BeProxyInterface beProxyInterface) {
    this.beProxyInterface = beProxyInterface;
  }

  public BeProxyInterface getProxy() {
    return (BeProxyInterface)
        Proxy.newProxyInstance(
            this.getClass().getClassLoader(), new Class[] {BeProxyInterface.class}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before invoke");
    method.invoke(beProxyInterface, args);
    System.out.println("after invoke");
    return null;
  }
}
