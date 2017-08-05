package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class StackImplementationUsingLinkedListExample {
    public static void main(String[] args) {
        StackLinkedList stackLinkedList=new StackLinkedList();
        System.out.println("INSERTING AT FIRST (TOP) IN STACK IMPLEMENTED USING LINKED LIST ");
        stackLinkedList.push(39);
        stackLinkedList.displayStack();

        stackLinkedList.push(71);
        stackLinkedList.displayStack();

        stackLinkedList.push(11);
        stackLinkedList.displayStack();

        stackLinkedList.push(76);
        stackLinkedList.displayStack();

        System.out.println("\nDELETING FROM FIRST (TOP) FROM STACK IMPLEMENTED USING LINKED LIST ");
        stackLinkedList.pop();
        stackLinkedList.displayStack();

        stackLinkedList.pop();
        stackLinkedList.displayStack();

        stackLinkedList.pop();
        stackLinkedList.displayStack();

        stackLinkedList.pop();
        stackLinkedList.displayStack();
    }
}
