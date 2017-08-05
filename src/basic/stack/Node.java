package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public void displayNode(){
        System.out.print(data+" ");
    }
}
