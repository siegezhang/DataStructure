package thread;

/**
 * Created by siege on 2017/8/31.
 */
public class AddShutDownHookTest extends Thread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread started");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                System.out.println("executing shutdown hook");
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("shutdown hook executed successfully");
        }));
        Thread.sleep(4000);
        System.out.println("main thread ended");
    }

    /**OUTPUT
     *   main thread started
     *   main thread ended
     *   executing shutdown hook
     *   shutdown hook executed successfully
     *
     * */
}
