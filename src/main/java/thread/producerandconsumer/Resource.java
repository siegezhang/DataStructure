package thread.producerandconsumer;

public class Resource {

  private int number = 0;

  private boolean produced = false;

  public synchronized void create() {
    while (produced) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    number++;
    System.out.println(Thread.currentThread().getName() + "生产者------------" + number);
    produced = true;
    notifyAll();
  }

  public synchronized void destroy() {
    while (!produced) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + "消费者****" + number);
    produced = false;
    notifyAll();
  }
}
