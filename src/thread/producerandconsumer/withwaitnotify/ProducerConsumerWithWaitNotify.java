package thread.producerandconsumer.withwaitnotify;

import java.util.ArrayList;

/**
 * Created by siege on 2017/8/31.
 */

class  Producer implements Runnable{
    ArrayList<Integer> sharedQueue;

    public Producer() {
        sharedQueue=new ArrayList<>();
    }

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i <=10 ; i++) {
                sharedQueue.add(i);
                System.out.println("Producer is still Producing, Produced : "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Production is over, consumer can consume.");
            this.notify();
        }
    }
}

class Consumer extends Thread{
    Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        synchronized (this.producer){
            System.out.println("Consumer waiting for production to get over.");
            try {
                this.producer.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int productSize=this.producer.sharedQueue.size();
            for (int i = 0; i <productSize ; i++) {
                System.out.println("Consumed :"+this.producer.sharedQueue.remove(0)+" ");
                
            }

        }
    }
}


public class ProducerConsumerWithWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        Producer producer=new Producer();
        Consumer consumer=new Consumer(producer);

        Thread producerThread=new Thread(producer,"producer");
        Thread consumerThread=new Thread(consumer,"consumer");

        consumerThread.start();
        Thread.sleep(1000);

        producerThread.start();
    }
}
