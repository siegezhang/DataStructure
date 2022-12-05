package cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;

/** https://www.jianshu.com/p/9a61af393e41?from=timeline&isappinstalled=0 */
public class Client {
  @Test
  public void testCglib() {
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\test");
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloServiceImpl.class);
    // enhancer.setCallback(new HelloMethodInterceptor());
    enhancer.setCallback(
        (MethodInterceptor)
            (o, method, objects, methodProxy) -> {
              System.out.println("Before:" + method.getName());
              Object object = methodProxy.invokeSuper(o, objects);
              System.out.println("After:" + method.getName());
              return object;
            });
    // 我们也可以针对不同的方法调用不用的回调拦截，如下
    // enhancer.setCallbacks(new Callback[] {new HelloMethodInterceptor()});
    // enhancer.setCallbackFilter((Method method) -> 0);
    HelloServiceImpl helloService = (HelloServiceImpl) enhancer.create();
    helloService.sayHello();
    helloService.toString();
  }
}
