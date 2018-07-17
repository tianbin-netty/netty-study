package attribute;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @ProjectName: netty
 * @Title:
 * @Package attribute
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/5/22 17:29
 * @Version v1.0
 **/
public class ClienHandler extends ChannelInboundHandlerAdapter {
    
    public static final AttributeKey<ChannelID> Channel_ID = AttributeKey.valueOf("netty.channel");
    
    
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
//        super.channelActive(ctx);
        Attribute<ChannelID> attribute =  ctx.channel().attr(Channel_ID);
    
    
    
    
    
    
    }
    
    
    
    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
