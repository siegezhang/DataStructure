package basic.circularqueue;

/**
 * Created by siege on 2017/8/9.
 */
public class CircularQueueExample {
    public static void main(String[] args) {
        CircularQueue<Integer> circularQueue=new CircularQueue<Integer>(5);
        circularQueue.enqueue(11);
        circularQueue.enqueue(21);
        circularQueue.enqueue(31);
        circularQueue.enqueue(51);
        circularQueue.enqueue(61);

        System.out.print("Elements deQueued from circular Queue: ");
        System.out.print(circularQueue.dequeue()+" ");
        System.out.print(circularQueue.dequeue()+" ");
        System.out.print(circularQueue.dequeue()+" ");
        System.out.print(circularQueue.dequeue()+" ");
        System.out.print(circularQueue.dequeue()+" ");
    }
}
