package vavr;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VavrFunctionExample {

    @Test
    public void testCompose() {
        // 使用andThen
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        Assertions.assertEquals(6, add1AndMultiplyBy2.apply(2).intValue());

        // 使用compose
        Function1<Integer, Integer> add1AndMultiplyBy2WithCompose = multiplyByTwo.compose(plusOne);
        Assertions.assertEquals(6, add1AndMultiplyBy2WithCompose.apply(2).intValue());
    }

    @Test
    public void testLifting() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);
        Assertions.assertEquals("None", i1.toString());

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);
        Assertions.assertEquals(2, i2.get().intValue());
    }

    @Test
    public void testCurried() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        Assertions.assertEquals(6, add2.apply(4).intValue());
    }

    @Test
    public void testMemorize() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        Assertions.assertTrue(randomValue1 == randomValue1);
    }
}
