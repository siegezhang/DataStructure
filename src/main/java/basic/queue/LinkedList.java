package basic.queue;

public class LinkedList{
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
