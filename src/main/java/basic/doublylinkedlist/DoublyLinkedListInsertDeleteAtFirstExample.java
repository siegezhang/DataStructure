package basic.doublylinkedlist;

/**
 * Created by siege on 2017/8/8.
 */
public class DoublyLinkedListInsertDeleteAtFirstExample {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.insertFirst(11);
        linkedList.insertFirst(21);
        linkedList.insertFirst(59);
        linkedList.insertFirst(14);
        linkedList.insertFirst(39);


        linkedList.displayFrwd();
        linkedList.displayBckwrd();

        System.out.print("Deleted Nodes: ");
        Node deleteNode=linkedList.deleteFirst();
        deleteNode.displayNode();
        deleteNode=linkedList.deleteFirst();
        deleteNode.displayNode();

        System.out.println();
        linkedList.displayFrwd();
        linkedList.displayBckwrd();
    }
}
