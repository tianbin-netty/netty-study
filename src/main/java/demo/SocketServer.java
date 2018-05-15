package demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ProjectName: netty
 * @Title:
 * @Package demo
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/5/8 20:32
 * @Version v1.0
 **/
public class SocketServer {
    
    
    
    public static  void main(String args[]){
        
        //接收连接，但不处理
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //真正处理连接的group
        EventLoopGroup childGroup = new NioEventLoopGroup();
    
    
        try {
            //加载Initializer
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    //这里的childHandler是服务于childGroup的,如果直接使用
                    //handler方法添加处理器,则是服务于parentGroup的
                    .childHandler(new SocketServerInitializer());
        
            //绑定监听端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }
        finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
