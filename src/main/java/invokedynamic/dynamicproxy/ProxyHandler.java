package invokedynamic.dynamicproxy;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    public ProxyHandler() {
    }

    public static void main(String[] args) {
        // 它的作用是帮我们把JDK动态生成的proxy class 的字节码保存到硬盘中,注意：此处是jdk11对应的参数，不同版本可能有所不同
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        BeProxyInterface beProxyInterface = new BeProxyInterfaceImpl();
        BeProxyInterface proxy = (BeProxyInterface) new ProxyHandler().bind(beProxyInterface);
        proxy.testMethod();
    }

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
