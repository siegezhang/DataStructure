package vavr;

import io.vavr.concurrent.Future;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class FutureTest {
  @Test
  public void testFutureFailure() {
    final var word = "hello world";
    io.vavr.concurrent.Future.of(Executors.newFixedThreadPool(1), () -> word)
        .onFailure(throwable -> Assert.fail("不应该走到 failure 分支"))
        .onSuccess(result -> Assert.assertEquals(word, result));
  }

  @Test
  public void testFutureSuccess() {
    io.vavr.concurrent.Future.of(
            Executors.newFixedThreadPool(1),
            () -> {
              throw new RuntimeException();
            })
        .onFailure(throwable -> Assert.assertTrue(throwable instanceof RuntimeException))
        .onSuccess(result -> Assert.fail("不应该走到 success 分支"));
  }

  @Test
  public void testToJavaFuture() {
    Future.of(Executors.newFixedThreadPool(1), () -> "toJava").toCompletableFuture();
    Future.fromCompletableFuture(CompletableFuture.runAsync(() -> {}));
  }
}
