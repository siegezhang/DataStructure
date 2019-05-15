package cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;
/** https://www.jianshu.com/p/9a61af393e41?from=timeline&isappinstalled=0 */
public class Client {
  @Test
  public void testCglib() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloServiceImpl.class);
    enhancer.setCallback(new HelloMethodInterceptor());
    HelloServiceImpl helloService = (HelloServiceImpl) enhancer.create();
    helloService.sayHello();
  }
}
