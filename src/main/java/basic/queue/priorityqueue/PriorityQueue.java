package basic.queue.priorityqueue;

/**
 * Created by siege on 2017/8/7.
 *
 * Priority Queue is Collection of entities or elements in which >
 * Addition of element is done on basis of priority.
 * Removal of element is done at FRONT.
 *
 * Insert - O(n) [as we insert element in middle of priority queue on basis of priority]
 * Remove - O(1) [as we remove element from front of priority queue in java]
 */
public class PriorityQueue {
    private int[] prioQueueAr;
    private int size;
    private int number;

    public PriorityQueue(int size) {
        this.size = size;
        prioQueueAr=new int[size];
        number=0;
    }

    public void insert(int value){
        int i;
        if (isFull())
            throw  new QueueFullException("Cannot insert "+value+", Queue is full");
        if (number==0)
            prioQueueAr[number++]=value;
        else {
            for (i=number-1;i>=0;i--){
                if (value>prioQueueAr[i])
                    prioQueueAr[i+1]=prioQueueAr[i];
                else
                    break;
            }
            prioQueueAr[++i]=value;
            number++;
        }
    }

    public int remove(){
        if (isEmpty())
            throw new QueueEmptyException("Queue is empty");
        return prioQueueAr[--number];
    }

    public boolean isEmpty() {
        return number==0;
    }


    public boolean isFull() {
        return number==size;
    }
}
