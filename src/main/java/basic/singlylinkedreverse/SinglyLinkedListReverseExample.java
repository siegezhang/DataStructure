package basic.singlylinkedreverse;

/**
 * Created by siege on 2017/8/8.
 */
public class SinglyLinkedListReverseExample {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.insertFirst(4);
        linkedList.insertFirst(3);
        linkedList.insertFirst(2);
        linkedList.insertFirst(1);

        linkedList.displayLinkedList();
        linkedList.reverseLinkedList();
        linkedList.displayLinkedList();
    }
}
