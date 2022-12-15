package vavr;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

public class EitherTest {
  @Test
  public void test1() {}

  public Either<Exception, String> compute() {
    // ...
    return Either.right("Hello");
  }

  public void test() {
    Either<Exception, String> either = compute();
    // 异常值
    if (either.isLeft()) {
      Exception exception = compute().getLeft();
      throw new RuntimeException(exception);
    }

    // 正确值
    if (either.isRight()) {
      String result = compute().get();
      // ...
    }
  }
}
