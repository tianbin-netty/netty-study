package chapter1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ProjectName: netty
 * @Title:
 * @Package chapter1
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/3/9 8:56
 * @Version v1.0
 **/
public class EchoClient {


    private  final  String host;
    private final  int port;


    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


     public  void start() throws  Exception{


         EventLoopGroup group=new NioEventLoopGroup();

         try {

             Bootstrap b=new Bootstrap();

             b.group(group)
                     .channel(NioSocketChannel.class)
                     .remoteAddress(new InetSocketAddress(host,port))
                     .handler(new ChannelInitializer<SocketChannel>() {
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

                             //同步增加
                             ch.pipeline().addLast(new EchoClientHandler());

                         }
                     });

                   ChannelFuture f=b.connect().sync();
                   f.channel().closeFuture().sync();

         }finally {

         }











     }












}
