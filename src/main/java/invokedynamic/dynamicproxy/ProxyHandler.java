package invokedynamic.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
  private Object target;

  public ProxyHandler(Object target) {
    this.target = target;
  }

  public ProxyHandler() {}

  public Object bind(Object target) {
    this.target = target;
    return Proxy.newProxyInstance(
        target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before invoke");
    method.invoke(target, args);
    System.out.println("after invoke");
    return null;
  }
}
