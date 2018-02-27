package basic.circularsinglylinkedList;

/**
 * Created by siege on 2017/8/7.
 * 这是关于单向有环链表的问题，快指针每次走2步，慢指针每次走一步，
 * 快指针和慢指针同时从起点出发，可以证明，快指针和慢指针最终会在圆环的某一个点相遇，同时
 * 第一次相遇慢指针并未走完一圈，慢指针再往前移从初始节点到Join处个节点就刚好到达Join点
 */
public class LinkedList {
    Node first=null;
    Node circularPoint1;
    Node circularPoint2;

    Node slowPointer;
    Node fastPointer;


    public void insert(int data){
        Node newNode=new Node(data);
        newNode.next=first;
        first=newNode;

        /*
        * Below we have kept track of two Nodes so that later we can make LinkedList circular(If required).
        * Note:- I have kept track of below two nodes just for demonstration purpose.
        * You may provide some other implementation for making LinkedList circular.
        */
        if (data==33)
            circularPoint1=newNode;
        if (data==66)
            circularPoint2=newNode;
    }

    /**
     *This method makes LikedList circular- by making end Node point to some middle Node of LinkedList.
     *end Node--->middle Node.
     */
    public void makeLinkedListCircular(){
        circularPoint2.next=circularPoint1;
        System.out.println("LinkedList has been succesfully converted into CircularLinkedList");
    }

    /**
     * method calculates number of nodes in circular LinkedList.
     */
    public void calculateNodesInCircularLinkedList(){
        slowPointer=first;
        fastPointer=first;

        while ((slowPointer!=fastPointer||slowPointer==first)&&fastPointer.next!=null&&fastPointer.next.next!=null){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
        }

        int noOfNodes=0;
        slowPointer=first;
        //可以证明在相遇处的快节点按照每次走一步，和从初始节点开始每次走一步的慢节点会在环初始节点相遇
        while (slowPointer!=fastPointer){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next;
            noOfNodes++;
        }
        System.out.println("LinkedList is circular at Node: "+slowPointer.data);

        boolean pointersJustmet=true;
        while (slowPointer!=fastPointer||pointersJustmet){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
            noOfNodes++;
            pointersJustmet=false;
        }
        System.out.println("Total number of nodes in LinkedList are: "+noOfNodes);
    }

    public void displayLinkedList(){
        Node tmpNode=first;
        int displayLimiterCtr=0;
        System.out.print("Displaying LinkedList [first--->last]: ");
        while (tmpNode!=null){
            tmpNode.displayNode();
            tmpNode=tmpNode.next;
            if (++displayLimiterCtr>=12)
                break;
        }
        System.out.println();
    }


}
