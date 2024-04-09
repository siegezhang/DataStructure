package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Example {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface Number {
        int value();
    }

    @Number(value = 42)
    public int adder(final int b) throws SecurityException, NoSuchMethodException {
        Number number = getClass().getMethod("adder", int.class).getAnnotation(Number.class);
        return addImpl(number, b);
    }

    public int addImpl(final Number a, final int b) {
        return a.value() + b;
    }

    public static void main(final String[] args) throws SecurityException, NoSuchMethodException {
        System.out.println(new Example().adder(0));
    }
}