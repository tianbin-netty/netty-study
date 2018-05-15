package chapter4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ProjectName: netty
 * @Title:
 * @Package chapter4
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/3/19 17:48
 * @Version v1.0
 **/
public class NettyOioServer {




    public  void server(int port) throws InterruptedException {

        final ByteBuf byteBuf= Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group=new OioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();

        bootstrap.group(group)
                 .channel(OioServerSocketChannel.class)
                 .localAddress(new InetSocketAddress(port))
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

                         ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){


                             /**
                              * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
                              * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
                              * <p>
                              * Sub-classes may override this method to change behavior.
                              *
                              * @param ctx
                              */
                             @Override
                             public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                 super.channelActive(ctx);

                                 //复制当前对象，复制后的对象与前对象共享缓冲区，且维护自己的独立索引
                                 ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);

                             }
                         });

                     }
                 });

        ChannelFuture channelFuture=bootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
    }
}
