import basic.circularqueue.CircularQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CircularQueueTest {
    private static CircularQueue<String> queue;

    @BeforeAll
    @DisplayName("初始化queue")
    static void init() {
        queue = new CircularQueue<>(2);
    }

    @Test
    @DisplayName("环形队列测试")
    void test() {
        queue.enqueue("hello");
        queue.enqueue("word");
        Assertions.assertTrue(queue.isFull());
        Assertions.assertEquals("hello", queue.dequeue());
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals("word", queue.dequeue());
        Assertions.assertTrue(queue.isEmpty());
    }
}
