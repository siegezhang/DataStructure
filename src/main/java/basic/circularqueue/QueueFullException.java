package basic.circularqueue;

/**
 * Created by siege on 2017/8/9.
 */
public class QueueFullException extends RuntimeException {
    public QueueFullException() {
    }

    public QueueFullException(String message) {
        super(message);
    }
}
