package thread.join;


import org.junit.jupiter.api.Test;

/**
 * Created by siege on 2017/9/3.
 */
class MyRunnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("in run() method");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i="+i+" ,ThreadName="+Thread.currentThread().getName());
        }
    }
}
public class JoinTest {
    @Test
    public void test() throws InterruptedException {
        System.out.println("In main() method");
        MyRunnable1 runnable1=new MyRunnable1();
        Thread thread1=new Thread(runnable1,"Thread-1");
        Thread thread2=new Thread(runnable1,"Thread-2");
        thread1.start();
        thread1.join(1000);
        thread2.start();
        thread2.join();
        System.out.println("end main() method");
    }

}
