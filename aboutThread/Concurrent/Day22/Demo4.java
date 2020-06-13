package aboutThread.Concurrent.Day22;

import java.lang.reflect.Field;
import java.util.Currency;
import java.util.concurrent.CountDownLatch;

import sun.misc.Unsafe;

public class Demo4 {
    static Unsafe unsafe;
    //用来记录网站的访问量，每次访问+1
    static int count;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟访问一次
     */
    @Deprecated
    public static void request(){
         unsafe.monitorEnter(Demo4.class); 
         try {
             count++;
         } catch (Exception e) {
             unsafe.monitorExit(Demo4.class);
         }  
    }

    public static void main(String[] args) throws InterruptedException{
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() ->{
                try {
                    for(int j = 0; j < 10;j++){
                        request();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ",耗时：" + (endTime - startTime) + ",count="+ count);
    }
}