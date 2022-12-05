package thread.producerandconsumer.withoutwaitnotify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Created by siege on 2017/9/1.
 */

class Producer implements Runnable{
    boolean productionInProcess;
    ArrayList<Integer> list;

    public Producer() {
        productionInProcess=true;
        list=new ArrayList<Integer>();
    }

    @Override
    public void run() {
        for (int i = 0; i <=10 ; i++) {
            list.add(i);
            System.out.println("Producer is still Producing, Produced : "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productionInProcess=false;
    }
}

class Consumer extends Thread{
    Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (producer.productionInProcess){
            System.out.println("Consumer waiting for production to get over.");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Production is over, consumer can consume.");
        int producerSize=producer.list.size();
        for (int i = 0; i <producerSize ; i++) {
            System.out.println("Consumed : "+producer.list.remove(0)+" ");
        }
    }
}
public class ProducerConsumerWithoutWaitNotify {
    @Test
    public void test() {
        Producer producer=new Producer();
        Consumer consumer=new Consumer(producer);

        Thread producerThread=new Thread(producer,"producerThread");
        Thread consumerThread=new Thread(consumer,"consumerThread");

        producerThread.start();
        consumerThread.start();
    }




}
