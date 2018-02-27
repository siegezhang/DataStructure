package basic.singlylinkedreverse;

/**
 * Created by siege on 2017/8/8.
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public void dispalyNode(){
        System.out.print(data+" ");
    }
}
