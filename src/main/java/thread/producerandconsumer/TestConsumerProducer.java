package thread.producerandconsumer;

/** junit的单元测试中,用thread创建多个线程时,单元测试并不会等待主线程下启动的新线程是否执行结束,只 要主线程结束完成,单元测试就会关闭,导致主线程中启动的新线程不能顺利执行完! */
public class TestConsumerProducer {
  public static void main(String[] args) {
    Resource resource = new Resource();
    /*
    new Thread(new Producer(resource)).start();

    new Thread(new Consumer(resource)).start();*/

    /**
     * 当两个线程同时操作生产者生产或者消费者消费时，如果有生产者或者的两个线程都wait()时，
     * 再次notify(),由于其中一个线程已经改变了标记而另外一个线程再次往下直接执行的时候没有判断标记而导致的。
     * if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
     * 解决方案
     * while判断标记，解决了线程获取执行权后，是否要运行！也就是每次wait()后再notify()时先再次判断标记
     * while判断标记+notify会导致"死锁"
     * 解决方案
     * notify()->notifyAll()
     */
    new Thread(new Consumer(resource)).start(); // 消费者线程
    new Thread(new Consumer(resource)).start(); // 消费者线程
    new Thread(new Producer(resource)).start(); // 生产者线程
    new Thread(new Producer(resource)).start(); // 生产者线程
  }
}
