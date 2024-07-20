package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector in Java corresponds to an epoll instance in Linux. SelectableChannel corresponds to a
 * file descriptor set to non-blocking mode. SelectionKey corresponds to an epoll_event structure.
 * Interest Set (SelectionKey.OP_*) corresponds to the events field in epoll_event. Ready Set is
 * determined by the epoll_wait system call.
 */
public class ServerSocketChannelTest {
  public static void main(String[] args) throws IOException {
    // correspond to file descriptors in Linux. Non-blocking mode is set on these file descriptors
    // using the fcntl system call with the O_NONBLOCK flag.
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.socket().bind(new InetSocketAddress("127.0.0.1", 5000));
    ssc.configureBlocking(false);
    // Selector.open() is called, an epoll instance is created using the epoll_create system call
    // int epoll_fd = epoll_create1(0);
    Selector selector = Selector.open();
    // Channels are registered with the selector, and corresponding file descriptors are added to
    // the epoll instance. SelectionKey corresponds to the epoll_event structure ,This structure
    // contains information about the file descriptor and the events it is interested in or ready
    // for : EPOLLIN (input ready), EPOLLOUT (output ready), EPOLLPRI (urgent data ready)
    // Interest Set (SelectionKey.OP_*) Ready Set (subset of the interest set)
    // struct epoll_event ev;
    // ev.events = EPOLLIN; // Interested in input events
    // ev.data.fd = server_fd;
    // epoll_ctl(epoll_fd, EPOLL_CTL_ADD, server_fd, &ev);
    ssc.register(selector, SelectionKey.OP_ACCEPT);
    ByteBuffer readBuff = ByteBuffer.allocate(128);
    ByteBuffer writeBuff = ByteBuffer.allocate(128);
    writeBuff.put("received".getBytes());
    writeBuff.flip();
    while (true) {
      // selector.select() translates to a call to epoll_wait(), which waits for events on the
      // registered file descriptors
      // int num_events = epoll_wait(epoll_fd, events, MAX_EVENTS, -1);
      selector.select();
      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> keyIterator = keys.iterator();
      // for (int i = 0; i < num_events; i++) {
      //    if (events[i].events & EPOLLIN) {
      //        // Handle read
      //    }
      //    if (events[i].events & EPOLLOUT) {
      //        // Handle write
      //    }
      //    if (events[i].events & EPOLLERR) {
      //        // Handle error
      //    }
      // }
      while (keyIterator.hasNext()) {
        SelectionKey key = keyIterator.next();
        keyIterator.remove();
        if (key.isAcceptable()) {
          SocketChannel socketChannel = ssc.accept();
          socketChannel.configureBlocking(false);
          socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
          SocketChannel socketChannel = (SocketChannel) key.channel();
          readBuff.clear();
          socketChannel.read(readBuff);
          readBuff.flip();
          System.out.println(new String(readBuff.array()));
          key.interestOps(SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
          writeBuff.rewind();
          SocketChannel socketChannel = (SocketChannel) key.channel();
          socketChannel.write(writeBuff);
          key.interestOps(SelectionKey.OP_READ);
        }
      }
    }
  }
}
