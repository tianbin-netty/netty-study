package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @ProjectName: netty-restful-server
 * @Title:
 * @Package bytebuf
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/5/11 15:02
 * @Version v1.0
 **/
public class ByteBufT {
    
    
    public static  void main(String args[]){
    
    
        ByteBuf byteBuf =Unpooled.buffer();
    
        byteBuf.discardReadBytes();
        byteBuf.discardSomeReadBytes();
    
        
    
    }
    
}
