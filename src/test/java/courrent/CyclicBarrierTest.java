package courrent;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * @ProjectName: netty
 * @Title:
 * @Package courrent
 * @Description: (用一句话描述该文件做什么)
 * @User tianbin
 * @Date 2018/7/4 15:13
 * @Version v1.0
 **/
public class CyclicBarrierTest {
    
    private static CyclicBarrier cyclicBarrier;
    
    static class CyclicBarrierThread extends Thread{
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("人到齐了，开会吧....");
            }
        });
        
        for(int i = 0 ; i < 5 ; i++){
            new CyclicBarrierThread().start();
        }
    }
}
