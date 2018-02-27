package basic.queue.priorityqueue;

/**
 * Created by siege on 2017/8/7.
 *
*/
public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue priorityQueue=new PriorityQueue(10);

        priorityQueue.insert(81);
        priorityQueue.insert(72);
        priorityQueue.insert(52);
        priorityQueue.insert(61);

        System.out.print("Deleted elements from queue:");

        System.out.print(priorityQueue.remove()+" ");
        System.out.print(priorityQueue.remove()+" ");
        System.out.print(priorityQueue.remove()+" ");
        System.out.print(priorityQueue.remove()+" ");
    }
}
