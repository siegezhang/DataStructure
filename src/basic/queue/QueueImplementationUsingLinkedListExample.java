package basic.queue;

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
