package nio.bytebuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/** Created by siege on 2017/9/3. */
public class ServerSocketChannelTest {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.socket().bind(new InetSocketAddress("127.0.0.1", 5000));
    SocketChannel socketChannel = ssc.accept();
    ByteBuffer readBuffer = ByteBuffer.allocate(128);
    socketChannel.read(readBuffer);
    readBuffer.flip();
    while (readBuffer.hasRemaining()) {
      System.out.print((char) readBuffer.get());
    }
    socketChannel.close();
    ssc.close();
  }
}
