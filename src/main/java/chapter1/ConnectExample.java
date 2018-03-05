package chapter1;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ProjectName: netty
 * @Title:
 * @Package chapter1
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/3/3 16:08
 * @Version v1.0
 **/
public class ConnectExample {

       private static  final Channel CHANNEL_FROM_SOMEWHERE= (Channel) new NioSocketChannel();





        public  static  void connect(){

//            Channel channel=CHANNEL_FROM_SOMEWHERE;
//            ChannelFuture channelFuture=channel.connect(new InetSocketAddress("127.0.0.1",25))；
//            channelFuture.addListener((ChannelFutureListener) (future) -> {
//                if(future.isSuccess()){
//                    ByteBuf byteBuf= Unpooled.copiedBuffer("hello", Charset.defaultCharset());
//                    ChannelFuture wf=future.channel().writeAndFlush(byteBuf);
//                }else{
//
//                }
//            }))


        }











}
