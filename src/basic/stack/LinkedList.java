package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class LinkedList {
    private Node first;

    public LinkedList() {
        first=null;
    }

    public void insertFirst(int data){
        Node tmpNode=first;
        first=new Node(data);
        first.next=tmpNode;
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
