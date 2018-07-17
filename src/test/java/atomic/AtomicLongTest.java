package atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ProjectName: netty
 * @Title:
 * @Package atomic
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/6/28 16:34
 * @Version v1.0
 **/
public class AtomicLongTest {
    
    
    
    
    @Test
    public void cas(){
    
        AtomicLong update = new  AtomicLong();
        update.set(1);
        update.compareAndSet(1,2);
    
        AtomicLong update2 = new  AtomicLong();
    
        update2.set(10);
        update2.compareAndSet(10,20);
    
    }
    
}
