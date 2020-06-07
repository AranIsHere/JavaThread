package aboutThread.Concurrent.Day19;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo3 {
    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次 开始执行！"); 
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }   
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次 执行结束！");    
        }, 1,3,TimeUnit.SECONDS);           
    }
}