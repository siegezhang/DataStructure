package basic;

/**
 * Created by siege on 2017/8/5.
 */
/***
 * Exception to indicate that LinkedList is empty
 * Complexity of  Implement Queue using Linked List in java
 *    Insert - O(1) [as we insert element at Rear in java]
 *    Remove - O(1) [as we remove element from front in java]
 */
class LinkedListEmptyException extends  RuntimeException{
    public LinkedListEmptyException() {

    }

    public LinkedListEmptyException(String message) {
        super(message);
    }
}

/**
 *Exception to indicate that Queue is empty.
 */
class QueueEmptyException extends RuntimeException{
    public QueueEmptyException() {
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}

class Node{
    public  int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public void displayNode(){
        System.out.print(data+" ");
    }
}

class LinkedList{
    private Node first;

    public LinkedList() {
        first=null;
    }

    public void insertLast(int data){
        Node newNode=new Node(data);
        if (first==null){
            first=newNode;
            return;
        }
        Node tempNode=first;
        while (tempNode.next!=null){
            tempNode=tempNode.next;
        }
        tempNode.next=newNode;
    }

    public Node deleteFirst(){
        if (first==null)
            throw new LinkedListEmptyException("LinkedList doesn't contain any Nodes.");
        Node tmpNode=first;
        first=first.next;
        return tmpNode;
    }

    public void displayLinkedList(){
        Node tmpNode=first;
        while (tmpNode!=null){
            tmpNode.displayNode();
            tmpNode=tmpNode.next;
        }
        System.out.println();
    }
}

class QueueLinkedList{
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

public class QueueImplementationUsingLinkedListExample {
    public static void main(String[] args) {
        QueueLinkedList queueLinkedList=new QueueLinkedList();
        System.out.println("INSERTING AT LAST (REAR) IN QUEUE IMPLEMENTED USING LINKED LIST ");
        queueLinkedList.insert(11);
        queueLinkedList.displayStack();
        queueLinkedList.insert(71);
        queueLinkedList.displayStack();
        queueLinkedList.insert(39);
        queueLinkedList.displayStack();
        queueLinkedList.insert(31);
        queueLinkedList.displayStack();
        System.out.println("\nDELETING FROM FIRST (FRONT) OF QUEUE IMPLEMENTED USING LINKED LIST ");
        queueLinkedList.remove();
        queueLinkedList.displayStack();
        queueLinkedList.remove();
        queueLinkedList.displayStack();
        queueLinkedList.remove();
        queueLinkedList.displayStack();
        queueLinkedList.remove();
        queueLinkedList.displayStack();

    }
}
