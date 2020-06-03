package jvmlearning.localvariabletable;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author xiaosheng.zhang
 * @date 20200603
 */
public class MethodParameterTest {
    @Test
    public void methodParams() throws NoSuchMethodException {
        Class<TestClass> clazz = TestClass.class;
        Method method = clazz.getMethod("add", int.class, int.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter p : parameters) {
            System.out.println(p.getName());
        }
    }
}


class TestClass {
    public int add(int add1, int add2) {
        int add = 1;
        return add1 + add2 + add;
    }
}