package thread;

import java.util.function.Supplier;


public class Test {


    @org.junit.jupiter.api.Test
    public void test() {
        new Test().test(() -> "test");

        Supplier<Test> supplier;
        for (int i = 0; i < 10; i++) {
            supplier = Test::new;
            System.out.println(supplier.get());
        }
    }

    public <T> void test(Supplier<T> supplier) {
        System.out.println(supplier.get());
    }
}