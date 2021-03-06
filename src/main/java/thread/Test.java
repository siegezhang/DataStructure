package thread;

import java.util.function.Supplier;


public class Test {


    public static void main(String[] args) {
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