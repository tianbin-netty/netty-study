package chapter1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ProjectName: netty
 * @Title:
 * @Package chapter1
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/3/5 9:20
 * @Version v1.0
 **/
public class EchoServer {




    private  final  int port;

    public  EchoServer(int port){
        this.port=port;
    }


    public  static  void main(String[] args) throws Exception {

        if(args.length!=1){
          System.err.println("Usage:"+EchoServer.class.getSimpleName()+"<port>");
        }

        //设置端口值
        int port=Integer.parseInt(args[0]);
        //调用服务器的start()方法
        new EchoServer(port).start();
    }


    public void start() throws  Exception{


        final  EchoServerHandler serverHandler=new EchoServerHandler();

        //接受和处理新的连接
        EventLoopGroup group=new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    //设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        /**
                         * This method will be called once the {@link Channel} was registered. After the method returns this instance
                         * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
                         *
                         * @param ch the {@link Channel} which was registered.
                         * @throws Exception is thrown if an error occurs. In that case it will be handled by
                         *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
                         *                   the {@link Channel}.
                         */
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                                   ch.pipeline().addLast(serverHandler);
                        }
                    });


             //异步的绑定服务器.调用sync()方法阻塞等待直到绑定完成
             ChannelFuture channelFuture=bootstrap.bind().sync();
             //获取channel的closeFuture,并且阻塞当前线程直到完成
             channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }






}
