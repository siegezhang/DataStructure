package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class StackLinkedList {
    LinkedList linkedList=new LinkedList();

    public void push(int data){
        linkedList.insertFirst(data);
    }

    public Node pop(){
        try {
            return  linkedList.deleteFirst();
        }catch (LinkedListEmptyException  e){
            throw new StackEmptyException();
        }
    }

    public void displayStack(){
        System.out.print("Displaying Stack >  Top to Bottom : ");
        linkedList.displayLinkedList();
    }
}
