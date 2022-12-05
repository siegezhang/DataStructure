package thread.interrupt;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by siege on 2017/9/2.
 */

class MyRunnable implements Runnable{

    @Override
    public void run() {
        int i=0;
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);
                System.out.println(i++ + " Please press enter key to stop "+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " ENDED");
                //由于线程抛出InterruptedException异常，它清除中断状态，
                // 为了让上层代码知道该线程的状态，重新设置打断状态。
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

/**
 * 具体来说，当对一个线程，调用 interrupt() 时，
 * ① 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），
 *    那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
 * ② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。
 *    被设置中断标志的线程将继续正常运行，不受影响。
 *  interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。
 *
 *  也就是说，一个线程如果有被中断的需求，那么就可以这样做:
 *  ① 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
    ② 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）

     Thread thread = new Thread(() -> {
     while (!Thread.interrupted()) {
     // do more work.
     }
     });
     thread.start();

     // 一段时间以后
     thread.interrupt();
 * */
public class TerminatingThreadUsingInterrupt {
    @Test
    public void test() throws IOException {
        MyRunnable runnable=new MyRunnable();
        Thread t=new Thread(runnable,"Thread-1");
        t.start();
        System.out.println(Thread.currentThread().getName()+ " thread waiting for user to press enter");
        System.in.read();
        t.interrupt();
        System.out.println(Thread.currentThread().getName() + " thread ENDED");
    }
}

