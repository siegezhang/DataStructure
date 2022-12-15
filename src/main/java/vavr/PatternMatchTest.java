package vavr;

import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.google.common.base.Predicates.instanceOf;
import static io.vavr.API.*;
import static io.vavr.Patterns.*;

public class PatternMatchTest {

  @Test
  public void testMatchNone() {
    // 匹配 None
    var noneOpt = Option.none();
    Match(noneOpt)
        .of(
            Case(
                $None(),
                r -> {
                  Assert.assertEquals(Option.none(), r);
                  return true;
                }),
            Case($(), this::failed));
  }

  @Test
  public void testMatchValue() {
    // 匹配某一个值为 Nice 的 Some
    var opt2 = Option.of("Nice");
    Match(opt2)
        .of(
            Case(
                $Some($("Nice")),
                r -> {
                  Assert.assertEquals("Nice", r);
                  return true;
                }),
            Case($(), this::failed));
  }

  @Test
  public void testMatchAnySome() {
    // 匹配 Some，值任意
    var opt = Option.of("hello world");
    Match(opt)
        .of(
            Case($None(), this::failed),
            Case(
                $Some($()),
                r -> {
                  Assert.assertEquals("hello world", r);
                  return true;
                }));
  }

  private boolean failed() {
    Assert.fail("不应该执行该分支");
    return false;
  }

  @Test
  public void testMatchFailure() {
    var res =
        Try.of(
            () -> {
              throw new RuntimeException();
            });
    Match(res)
        .of(
            // 匹配成功情况
            Case($Success($()), r -> run(Assert::fail)),
            // 匹配异常为 RuntimeException
            Case($Failure($(instanceOf(RuntimeException.class))), r -> true),
            // 匹配异常为 IllegalStateException
            Case($Failure($(instanceOf(IllegalStateException.class))), r -> run(Assert::fail)),
            // 匹配异常为 NullPointerException
            Case($Failure($(instanceOf(NullPointerException.class))), r -> run(Assert::fail)),
            // 匹配其余失败的情况
            Case($Failure($()), r -> run(Assert::fail)));
  }

  @Test
  public void testMatchSuccess() {
    var res = Try.of(() -> "Nice");
    Match(res)
        .of(
            // 匹配任意成功的情况
            Case($Success($()), r -> run(() -> Assert.assertEquals("Nice", r))),
            // 匹配任意失败的情况
            Case($Failure($()), r -> run(Assert::fail)));
  }
}
