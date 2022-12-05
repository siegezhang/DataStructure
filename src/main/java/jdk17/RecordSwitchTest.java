package jdk17;

import org.junit.jupiter.api.Test;

sealed interface TwoDimensional {}

public class RecordSwitchTest {
    private static TwoDimensional getTwoDimensional() {
        return new Point(1,2);
    }

    static int process(TwoDimensional twoDim) {
        return switch (twoDim) {
            case Point(int x, int y) -> x + y;
            case Line(Point a, Point b) -> process(a) + process(b);
            case Triangle(Point a, Point b, Point c) ->
                    process(a) + process(b) + process(c);
            case Square(Point a, Point b, Point c, Point d) ->
                    process(a) + process(b) + process(c) + process(d);
        };
    }

@Test
public void testRecord(){
System.out.println(process(getTwoDimensional()));
}

record Point (int x, int y) implements TwoDimensional { }
record Line    ( Point start,
                 Point end) implements TwoDimensional { }
record Triangle( Point pointA,
                 Point pointB,
                 Point PointC) implements TwoDimensional { }
record Square  ( Point pointA,
                 Point pointB,
                 Point PointC,
                 Point pointD) implements TwoDimensional { }
}