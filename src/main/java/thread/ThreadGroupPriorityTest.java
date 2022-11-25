package thread;

import org.junit.Test;

/**
 * Created by siege on 2017/8/31.
 */
public class ThreadGroupPriorityTest {
    @Test
    public void test() {
        ThreadGroup threadGroup=new ThreadGroup("threadGroup");
        System.out.println ("threadGroup's default maximum priority = "+ threadGroup.getMaxPriority());

        Thread thread1=new Thread(threadGroup,"thread-1");
        System.out.println("thread1's default priority ="+thread1.getPriority());

        threadGroup.setMaxPriority(3);
        System.out.println("Now, threadGroup's maximum priority = "+threadGroup.getMaxPriority());
        System.out.println ("thread1's priority after changing ThreadGroups's priority = "+ thread1.getPriority ());

        Thread thread2=new Thread(threadGroup,"thread-2");
        System.out.println("thread2's default priority = "+thread2.getPriority());

        thread2.setPriority(10);
        System.out.println("thread2's priority after setPriority() = "+thread2.getPriority());
    }
}
