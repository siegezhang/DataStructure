package basic.circularsinglylinkedList;

/**
 * Created by siege on 2017/8/7.
 */
public class LinkedListCircularCalculateNodes {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.insert(66);
        linkedList.insert(55);
        linkedList.insert(44);
        linkedList.insert(33);
        linkedList.insert(22);
        linkedList.insert(11);

        linkedList.makeLinkedListCircular();
        linkedList.displayLinkedList();

        linkedList.calculateNodesInCircularLinkedList();
    }
}
