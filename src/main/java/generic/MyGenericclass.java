package generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyGenericclass<T> {
    public void printclass() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                Class<?> clazz = (Class<?>) actualTypeArguments[0];
                System.out.println(clazz);
            }
        }
    }

    public static void main(String[] args) {
        MyGenericclass<String> myGenericclass = new MyGenericclass<>();
        myGenericclass.printclass();//输出:class java.lang.string
        MyGenericclass<Integer> myGenericclass2 = new MyGenericclass<>();
        myGenericclass2.printclass();//输出:class java.lang.Integer
    }
}