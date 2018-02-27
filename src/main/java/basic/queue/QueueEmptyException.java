package basic.queue;

/**
 *Exception to indicate that Queue is empty.
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException() {
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
