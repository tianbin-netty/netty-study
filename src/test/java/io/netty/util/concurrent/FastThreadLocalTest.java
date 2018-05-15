package io.netty.util.concurrent;

import org.junit.Test;

/**
 * @ProjectName: netty
 * @Title:
 * @Package io.netty.util.concurrent
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/3/26 18:09
 * @Version v1.0
 **/
public class FastThreadLocalTest {

    private  static  final FastThreadLocal<String> fastThreadLocal=new FastThreadLocal<String>();
    
    
    
    
    
    
    @Test
    public  void  set(){
        fastThreadLocal.set("hello,test");
        System.out.println(fastThreadLocal.get());
    }
    
    
    
    



}
