package nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
  @Test
  public void test1() throws IOException {
    RandomAccessFile file = new RandomAccessFile("text.txt", "rw");
    FileChannel fileChannel = file.getChannel();

    ByteBuffer byteBuffer = ByteBuffer.allocate(48);

    int bytesRead = fileChannel.read(byteBuffer);

    while (bytesRead != -1) {
      System.out.println("read:"+bytesRead);
      byteBuffer.flip();
      while (byteBuffer.hasRemaining()) System.out.println((char)byteBuffer.get());
      byteBuffer.clear();
      bytesRead=fileChannel.read(byteBuffer);

    }
    file.close();
}
}
