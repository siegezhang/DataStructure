package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by siege on 2017/9/4.
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",5000));
        ByteBuffer readBuffer=ByteBuffer.allocate(32);
        ByteBuffer writeBuffer=ByteBuffer.allocate(32);
        writeBuffer.put("hello".getBytes());
        writeBuffer.flip();
        while (true){
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            readBuffer.clear();
            socketChannel.read(readBuffer);
        }

    }
}
