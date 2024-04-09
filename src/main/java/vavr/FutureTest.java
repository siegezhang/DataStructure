package vavr;

import io.vavr.concurrent.Future;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class FutureTest {
    @Test
    public void testFutureFailure() {
        final var word = "hello world";
        io.vavr.concurrent.Future.of(Executors.newFixedThreadPool(1), () -> word)
                .onFailure(throwable -> Assertions.fail("不应该走到 failure 分支"))
                .onSuccess(result -> Assertions.assertEquals(word, result));
    }

    @Test
    public void testFutureSuccess() {
        io.vavr.concurrent.Future.of(
                        Executors.newFixedThreadPool(1),
                        () -> {
                            throw new RuntimeException();
                        })
                .onFailure(throwable -> Assertions.assertTrue(throwable instanceof RuntimeException))
                .onSuccess(result -> Assertions.fail("不应该走到 success 分支"));
    }

    @Test
    public void testToJavaFuture() {
        Future.of(Executors.newFixedThreadPool(1), () -> "toJava").toCompletableFuture();
        Future.fromCompletableFuture(CompletableFuture.runAsync(() -> {
        }));
    }
}
