package basic.doublylinkedlist;

/**
 * Created by siege on 2017/8/8.
 */
public class LinkedList {
    private Node first;
    private Node last;

    public LinkedList() {
        first=null;
    }

    public void insertFirst(int data){
        Node newNode=new Node(data);
        if (first==null)
            last=newNode;
        else
            first.previous=newNode;
        newNode.next=first;
        first=newNode;
    }

    public Node deleteFirst(){
        if (first==null)
            throw new LinkedListEmptyException("LinkedList doesn't contain any Nodes");
        Node tmpNode=first;
        if (first.next==null)
            last=null;
        else
            first.next.previous=null;
        first=first.next;
        return tmpNode;
    }

    public void displayFrwd(){
        System.out.print("Displaying in forward direction [first--->last] : ");
        Node tmpNode=first;
        while (tmpNode!=null){
            tmpNode.displayNode();
            tmpNode=tmpNode.next;
        }
        System.out.println("");
    }

    public void displayBckwrd(){
        System.out.print("Displaying in backward direction [last-->first] : ");
        Node tmpNode=last;
        while (tmpNode!=null){
            tmpNode.displayNode();
            tmpNode=tmpNode.previous;
        }
        System.out.println("");
    }
}
