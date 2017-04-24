package tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author yutong
 * @version 1.0
 * @description
 * @since 2017/4/25 00:36
 */
public class TCPServerNio {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        int port = 9001;
        ssChannel.bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT); //注册监听连接请求
        while (true) {
            selector.select();//阻塞 直到某个channel注册的事件被触发
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                if (key.isAcceptable()) { //客户端连接请求
                    ServerSocketChannel ssc = (ServerSocketChannel) key
                            .channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ); //注册监听客户端输入
                }
                if (key.isReadable()) { //客户端输入
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
                    buffer.flip();
                    sc.write(buffer);
                }
            }
            keys.clear();
        }
    }
}
