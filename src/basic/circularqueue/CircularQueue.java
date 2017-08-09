package basic.circularqueue;

/**
 * Created by siege on 2017/8/9.
 */
public class CircularQueue<E> {
    private E[] circularQueueAr;
    private int maxSize;

    private int rear;
    private int front;
    private int number;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        circularQueueAr= (E[]) new Object[maxSize];
        number=0;
        rear=0;
        front =0;
    }

    public void enqueue(E item) throws QueueFullException{
        if (isFull())
            throw new QueueFullException("Circular Queue is full");
        else{
            circularQueueAr[rear]=item;
            rear=(rear+1)%circularQueueAr.length;
            number++;
        }
    }

    public E dequeue()throws QueueEmptyException{
        E deQueuedElement;
        if (isEmpty())
            throw new QueueEmptyException("Circular Queue is empty");
        else {
            deQueuedElement=circularQueueAr[front];
            circularQueueAr[front]=null;
            front=(front+1)%circularQueueAr.length;
            number--;
        }
        return deQueuedElement;

    }

    public boolean isEmpty() {
        return number==0;
    }

    public boolean isFull() {
        return number==circularQueueAr.length;
    }


}
