package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.OP_READ;

/**
 * @ProjectName: netty
 * @Title:
 * @Package nio
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/4/28 9:44
 * @Version v1.0
 **/
public class NioEchoServer {
    
    
    private static  final int BUF_SIZE=256;
    
    private static  final int TIMEOUT = 3000;
    
    public  static  void main(String args[]) throws IOException {
        //打开服务端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        
        //打开Selector
        Selector selector = Selector.open();
        
        //服务端Socket监听8080端口,并配置未非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //IOUtil.configureBlocking，底层为native方法
        serverSocketChannel.configureBlocking(false);
        
        //将chnannel注册到selector中
        //通常我们都是先注册一个OP_ACCEPT事件，然后在OP_ACCEPT到来时,再将这个Channel的OP_READ
        //注册到Selector
        
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        
        while (true){
        
            //通过调用select方法，阻塞的等待channel I/O可操作
            if(selector.select(TIMEOUT)==0){
                System.out.println(".");
                continue;
            }
           
            //获取 I/O操作就绪的SelectionKey,通过SelectionKey可以知道哪些Channel的哪类I/O操作已经就绪
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            
            while (keyIterator.hasNext()){
                 SelectionKey key = keyIterator.next();
                 //当获取一个SelectionKey之后,要将它删除,表示我们已经对这个IO事件做了处理
    
                 //当获取一个 SelectionKey 后, 就要将它删除, 表示我们已经对这个 IO 事件进行了处理
                keyIterator.remove();
    
                if (key.isAcceptable()) {
                    // 当 OP_ACCEPT 事件到来时, 我们就有从 ServerSocketChannel 中获取一个 SocketChannel,
                    // 代表客户端的连接
                    // 注意, 在 OP_ACCEPT 事件中, 从 key.channel() 返回的 Channel 是 ServerSocketChannel.
                    // 而在 OP_WRITE 和 OP_READ 中, 从 key.channel() 返回的是 SocketChannel.
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    //在 OP_ACCEPT 到来时, 再将这个 Channel 的 OP_READ 注册到 Selector 中.
                    // 注意, 这里我们如果没有设置 OP_READ 的话, 即 interest set 仍然是 OP_CONNECT 的话, 那么 select 方法会一直直接返回.
                    clientChannel.register(key.selector(), OP_READ, ByteBuffer.allocate(BUF_SIZE));
                }
                
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    long bytesRead = clientChannel.read(buf);
                    if (bytesRead == -1) {
                        clientChannel.close();
                    } else if (bytesRead > 0) {
                        key.interestOps(OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("Get data length: " + bytesRead);
                    }
                }
    
                if (key.isValid() && key.isWritable()) {
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    buf.flip();
                    SocketChannel clientChannel = (SocketChannel) key.channel();
        
                    clientChannel.write(buf);
        
                    if (!buf.hasRemaining()) {
                        key.interestOps(OP_READ);
                    }
                    buf.compact();
                }
            }
        }
    }
}
