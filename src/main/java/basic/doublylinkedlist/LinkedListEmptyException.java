package basic.doublylinkedlist;

/**
 * Created by siege on 2017/8/8.
 */
public class LinkedListEmptyException extends RuntimeException{
    public LinkedListEmptyException() {
    }

    public LinkedListEmptyException(String message) {
        super(message);
    }
}
