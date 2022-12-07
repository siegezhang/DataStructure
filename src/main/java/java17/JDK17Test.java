package java17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class JDK17Test {
    @Test
    public void testSwitch() {
        String result = formatterPatternSwitch(1);
        Assertions.assertEquals(result, "int 1");
        result = formatterPatternSwitch(1L);
        Assertions.assertEquals(result, "long 1");
        result = formatterPatternSwitch(1D);
        Assertions.assertEquals(result, "double 1.000000");
        result = formatterPatternSwitch("1");
        Assertions.assertEquals(result, "String 1");
    }

    @Test
    public void testRecord() {
        MyRecord record = new MyRecord("siege", 10);
        System.out.println(record);
    }

    @Test
    public void testSealed() {

    }

    static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }


    public abstract sealed class Shape
            permits Circle, Rectangle, Square, WeirdShape {
    }

    public final class Circle extends Shape {
    }

    public sealed class Rectangle extends Shape
            permits TransparentRectangle, FilledRectangle {
    }

    public final class TransparentRectangle extends Rectangle {
    }

    public final class FilledRectangle extends Rectangle {
    }

    public final class Square extends Shape {
    }

    public non-sealed class WeirdShape extends Shape {
    }


}


