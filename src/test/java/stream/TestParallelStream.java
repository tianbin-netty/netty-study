package stream;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: netty
 * @Title:
 * @Package stream
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/7/4 14:38
 * @Version v1.0
 **/
public class TestParallelStream {
    
    
    @Test
    public void testStream(){
        
        
        //起始时间
        LocalTime start = LocalTime.now();
    
        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }
    
        list.stream()// 获取串行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()
    
        // 终止时间
        LocalTime end = LocalTime.now();
    
        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
        
    }
    
    
    @Test
    public void testParallelStream() {
        // 起始时间
        LocalTime start = LocalTime.now();
        
        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }
        
        list.parallelStream()// 获取并行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()
        
        // 终止时间
        LocalTime end = LocalTime.now();
        
        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
    }
    
    
    
    
    
    
}
