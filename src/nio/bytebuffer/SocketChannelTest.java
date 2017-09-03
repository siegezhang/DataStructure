package nio.bytebuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by siege on 2017/9/3.
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=null;
        socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",5000));
        ByteBuffer writeBuffer=ByteBuffer.allocate(128);
        writeBuffer.put("hello world".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        socketChannel.close();

    }
}
