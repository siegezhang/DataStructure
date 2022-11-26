package java8.function;

import org.junit.Test;

import java.util.function.UnaryOperator;

public class UnaryOperatorTest {
  /**
   * andThen 和compose 的区别是参数执行的顺序
   *
   * <p>andThen - 先执行被调用的函数，最后执行参数
   *
   * <p>compose - 先执行参数，最后执行被调用的函数。
   */
  @Test
  public void test() {
    UnaryOperator<Integer> unaryOperator1 = value -> value * value;
    UnaryOperator<Integer> unaryOperator2 = value -> value * 10;
    System.out.println(unaryOperator1.andThen(unaryOperator2).apply(3));
    System.out.println(unaryOperator1.compose(unaryOperator2).apply(3));
  }
}
