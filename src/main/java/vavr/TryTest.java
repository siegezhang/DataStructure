package vavr;

import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TryTest {
    @Test
    public void test() {
        Try.of(() -> 1 / 0)
                .andThen(r -> System.out.println("and then " + r))
                .onFailure(error -> System.out.println("failure" + error.getMessage()))
                .andFinally(() -> System.out.println("finally"));
    }

    @Test
    public void testTryInstance() {
        // 除以 0 ，构建出 Failure
        var error = Try.of(() -> 0 / 0);
        Assertions.assertTrue(error instanceof Try.Failure);

        // 合法的加法，构建出 Success
        var normal = Try.of(() -> 1 + 1);
        Assertions.assertTrue(normal instanceof Try.Success);
    }

    @Test
    public void testTryWithRecover() {
        Assertions.assertEquals("NPE", testTryWithRecover(new NullPointerException()));
        Assertions.assertEquals("IllegalState", testTryWithRecover(new IllegalStateException()));
        Assertions.assertEquals("Unknown", testTryWithRecover(new RuntimeException()));
    }

    private String testTryWithRecover(Exception e) {
        return (String)
                Try.of(
                                () -> {
                                    throw e;
                                })
                        .recoverWith(NullPointerException.class, Try.of(() -> "NPE"))
                        .recoverWith(IllegalStateException.class, Try.of(() -> "IllegalState"))
                        .recoverWith(RuntimeException.class, Try.of(() -> "Unknown"))
                        .get();
    }

    @Test
    public void testTryMap() {
        String res =
                Try.of(() -> "hello world").map(String::toUpperCase).toOption().getOrElse(() -> "default");
        Assertions.assertEquals("HELLO WORLD", res);
    }
}
