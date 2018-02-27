package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class LinkedListEmptyException extends RuntimeException {
    public LinkedListEmptyException() {
    }

    public LinkedListEmptyException(String message) {
        super(message);
    }
}
