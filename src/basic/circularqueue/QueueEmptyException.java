package basic.circularqueue;

/**
 * Created by siege on 2017/8/9.
 */
public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException() {
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
