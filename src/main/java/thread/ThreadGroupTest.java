package thread;

import org.junit.Test;

/**
 * Created by siege on 2017/8/31.
 */
public class ThreadGroupTest {
    @Test
    public void test() {
        ThreadGroup threadGroupMain=Thread.currentThread().getThreadGroup();
        System.out.println("current ThreadGroup name ="+threadGroupMain.getName());

        MyRunnable myRunnable=new MyRunnable();

        ThreadGroup childThreadGroup1=new ThreadGroup("childThreadGroup1");
        Thread thread1=new Thread(childThreadGroup1,myRunnable,"Thread-1");
        thread1.start();

        ThreadGroup childThreadGroup2=new ThreadGroup("childThreadGroup2");
        Thread thread2=new Thread(childThreadGroup2,myRunnable,"Thread-2");
        thread2.start();

        System.out.println("Currently active groups  in main ThreadGroup ="+threadGroupMain.activeGroupCount());
        System.out.println("Currently active threads in main ThreadGroup ="+threadGroupMain.activeCount());

        System.out.println("\nchildThreadGroup1's parent ThreadGroup ="+childThreadGroup1.getParent().getName());
        System.out.println("Thread-1's ThreadGroup ="+thread1.getThreadGroup().getName());

        System.out.println("\nchildThreadGroup2's parent ThreadGroup ="+childThreadGroup2.getParent().getName());
        System.out.println("Thread-2's ThreadGroup ="+thread2.getThreadGroup().getName());

        System.out.println("\n--- list() method - Prints information about thread group ---");
        threadGroupMain.list();







    }
}
class MyRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
