package basic.queue.priorityqueue;

/**
 * Created by siege on 2017/8/7.
 */
public class QueueFullException extends RuntimeException {
    public QueueFullException() {
    }

    public QueueFullException(String message) {
        super(message);
    }
}
