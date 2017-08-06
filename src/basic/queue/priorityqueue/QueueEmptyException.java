package basic.queue.priorityqueue;

/**
 * Created by siege on 2017/8/7.
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException() {
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
