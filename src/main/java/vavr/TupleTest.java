package vavr;

import io.vavr.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

public class TupleTest {
  public void tupleTest() {
    Tuple2<String, Integer> tuple2 = Tuple.of("Hello", 100);
    Tuple2<String, Integer> updatedTuple2 = tuple2.map(String::toUpperCase, v -> v * 5);
    String result = updatedTuple2.apply((str, number) -> String.join(", ", str, number.toString()));
    System.out.println(result);
  }

  public void functionTest() {
    // 函数定义,前3个是入参，最后是R
    Function3<Integer, Integer, Integer, Integer> function3 = (v1, v2, v3) -> (v1 + v2) * v3;
    // 函数的组合
    Function3<Integer, Integer, Integer, Integer> composed = function3.andThen(v -> v * 100);
    // 执行结果
    int result = composed.apply(1, 2, 3);
    System.out.println(result);
    // 还有包含部分函数的应用
    Function1<Integer, Integer> function1 = function3.apply(1, 2);
    Integer apply = function1.apply(1);
    System.out.println(apply);
    // 项目联想
    // 结合项目场景使用比如说PDT中定义几个 函数比如：计算MP时效的函数，计算ALG的函数时效，在定义一些函数结果的拼接等
  }

  /**
   * 柯里化想要解决的问题： 柯里化方法的使用 柯里化的含义：
   * 柯里化（currying）是与λ演算相关的重要概念。通过柯里化，可以把有多个输入的函数转换成只有一个输入的函数，从而可以在λ演算中来表示。 柯里化的名称来源于数学家 Haskell
   * Curry。 Haskell Curry 是一位传奇性的人物，以他的名字命令了 3 种编程语言，Haskell、Brook 和 Curry。
   * 柯里化是把有多个输入参数的求值过程，转换成多个只包含一个参数的函数的求值过程。 对于清单 6 的函数 f(a, b, c)，在柯里化之后转换成函数 g，则对应的调用方式是
   * g(a)(b)(c)。 函数 (x, y) -> x + y 经过柯里化之后的结果是 x -> (y -> x + y)。
   */
  public void curriedTest() {
    // 设置函数 v1 v
    Function3<Integer, Integer, Integer, Integer> function3 = (v1, v2, v3) -> (v1 + v2) * v3;

    // 可以看出来是返回来了一个函数
    Function1<Integer, Function1<Integer, Integer>> apply = function3.curried().apply(1);

    // 多次柯里化后就会返回我们想要的记过
    int result = function3.curried().apply(1).curried().apply(2).curried().apply(3);
    System.out.println(result);
  }

  /** 记忆化方法 会将之前计算过的方法进行存储，相同参数在第二次调用的时候会使用缓存 */
  public void memoized() {
    // 计算差方
    Function2<BigInteger, Integer, BigInteger> pow = BigInteger::pow;
    // 记忆化
    Function2<BigInteger, Integer, BigInteger> memoized = pow.memoized();
    long start = System.currentTimeMillis();
    memoized.apply(BigInteger.valueOf(1024), 1024);
    long end1 = System.currentTimeMillis();
    memoized.apply(BigInteger.valueOf(1024), 1024);
    long end2 = System.currentTimeMillis();
    System.out.printf("%d ms -> %d ms", end1 - start, end2 - end1);
  }

  /** java 8 中的optional 是类似 其目的都是为了避免NPE的出现 */
  public static void optionTest() {
    // 个人觉得option好用的地方在于这个of 静态函数。
    Option<String> str = Option.of("Hello");

    str.map(String::length);
    // 使用对应的值
    Option<Integer> integers = str.flatMap(v -> Option.of(v.length()));
    boolean empty = integers.isEmpty();
    System.out.println(integers);
  }

  /** either 的使用 包含两个值，left（异常值） 和 right（正确值） */
  public void eitherAndTryTest() {

    // 这个是
    Either<String, String> either =
        compute().map(str -> str + " World").mapLeft(Throwable::getMessage);
    System.out.println(either);

    // Try的使用，不用写过多的catch，左后将left值交给某一个函数统一处理，
    // 在pdt中有很多这样的代码，try catch 嵌套起来使用 包含参数定义的参数校验异常
    //
    Fruit.fromColor("1111");
  }

  /**
   * Lazy 表示的是一个延迟计算的值。在第一次访问时才会进行求值操作，而且该值只会计算一次。之后的访问操作获取的是缓存的值。 Lazy.of 从接口 Supplier 中创建 Lazy
   * 对象。方法 isEvaluated 可以判断 Lazy 对象是否已经被求值。
   */
  public void lazyTest() {
    Lazy<BigInteger> lazy = Lazy.of(() -> BigInteger.valueOf(1024).pow(1024));
    System.out.println(lazy.isEvaluated());
    System.out.println(lazy.get());
    System.out.println(lazy.isEvaluated());
    System.out.println(lazy.get());
    System.out.println(lazy.isEvaluated());
    Lazy<BigInteger> lazy2 = Lazy.of(() -> BigInteger.valueOf(1024).pow(1024));
    System.out.println(lazy2.isEvaluated());
    System.out.println(lazy2.get());
  }

  private static ThreadLocalRandom random = ThreadLocalRandom.current();

  private static Either<Throwable, String> compute() {
    return random.nextBoolean()
        ? Either.left(new RuntimeException("Boom!"))
        : Either.right("Hello");
  }

  @Getter
  public enum Fruit {
    /** APPLE */
    APPLE("APPLE", "BLACK"),
    /** BANANA */
    BANANA("BANANA", "BLUE"),
    /** ORANGE */
    NONE("ORANGE", "WHITE");
    private final String name;
    private final String color;

    Fruit(String name, String color) {
      this.name = name;
      this.color = color;
    }

    public static Fruit fromColor(String color) {
      return Try.of(
              () ->
                  Arrays.stream(Fruit.values())
                      .filter(t -> t.getColor().equals(color))
                      .findFirst()
                      .orElse(NONE))
          .toEither()
          .getOrElse(NONE);
    }
  }
}
