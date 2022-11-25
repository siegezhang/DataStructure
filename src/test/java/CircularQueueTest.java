import basic.circularqueue.CircularQueue;
import org.junit.BeforeAll;
import org.junit.DisplayName;
import org.junit.Test;

import static org.junit.Assertions.*;

class CircularQueueTest {
    private static CircularQueue<String> queue;

    @BeforeAll
    @DisplayName("初始化queue")
    static void init(){
        queue=new CircularQueue<>(2);
    }

    @Test
    @DisplayName("环形队列测试")
    void test(){
        queue.enqueue("hello");
        queue.enqueue("word");
        assertTrue(queue.isFull());
        assertEquals("hello",queue.dequeue());
        assertFalse(queue.isEmpty());
        assertEquals("word",queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
