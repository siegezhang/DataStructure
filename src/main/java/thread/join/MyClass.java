package thread.join;

import org.junit.jupiter.api.Test;

/**
 * Created by siege on 2017/9/1.
 */
class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("in run() method");
        for (int i = 0; i <5 ; i++) {
            System.out.println("i="+i+",ThreadName="+ Thread.currentThread().getName());
        }
    }
}
public class MyClass {
    @Test
    public void test() throws InterruptedException {
        System.out.println("In main() method");
        MyRunnable runnable=new MyRunnable();
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        System.out.println("end main() method");
    }
}
