package basic.stack;

/**
 * Created by siege on 2017/8/6.
 */
public class StackEmptyException extends RuntimeException {
    public StackEmptyException() {
    }

    public StackEmptyException(String message) {
        super(message);
    }
}
