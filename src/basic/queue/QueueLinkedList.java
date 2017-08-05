package basic.queue;

/**
 * Created by siege on 2017/8/5.
 */

public class QueueLinkedList{
    LinkedList linkedList=new LinkedList();

    /**
     * Insert element at rear in Queue
     */
    public void insert(int value){
        linkedList.insertLast(value);
    }
    /**
     * Removes elements from front of Queue
     */
    public void remove(){
        try {
            linkedList.deleteFirst();
        }catch (LinkedListEmptyException e){
            throw new QueueEmptyException();
        }
    }

    public void displayStack(){
        System.out.print("Displaying Queue> Front to Rear:");
        linkedList.displayLinkedList();
    }
}
