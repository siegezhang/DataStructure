package basic.singlylinkedreverse;

/**
 * Created by siege on 2017/8/8.
 */
public class LinkedList {
    private Node first;

    public LinkedList() {
        first=null;
    }

    public void reverseLinkedList(){
        Node previousNode=null;
        Node currentNode=first;
        Node nextNode=first;

        while (nextNode!=null){
            nextNode=nextNode.next;
            currentNode.next=previousNode;
            previousNode=currentNode;
            currentNode=nextNode;
        }

        first=previousNode;
        System.out.println("LinkedList has been reversed successfully");
    }

    public void insertFirst(int data){
        Node newNode=new Node(data);
        newNode.next=first;
        first=newNode;
    }

    public void displayLinkedList(){
        System.out.print("Displaying LinkedList [first--->last]: ");
        Node tmpNode=first;
        while (tmpNode!=null){
            tmpNode.dispalyNode();
            tmpNode=tmpNode.next;
        }
        System.out.println();
    }
}
