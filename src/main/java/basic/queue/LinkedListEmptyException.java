package basic.queue;

/***
 * Exception to indicate that LinkedList is empty
 * Complexity of  Implement Queue using Linked List in java
 *    Insert - O(1) [as we insert element at Rear in java]
 *    Remove - O(1) [as we remove element from front in java]
 */
public class LinkedListEmptyException extends  RuntimeException{
    public LinkedListEmptyException() {

    }

    public LinkedListEmptyException(String message) {
        super(message);
    }
}
