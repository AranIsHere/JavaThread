package aboutThread.Concurrent.Day18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
                //模拟任务内部处理耗时
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
            });
        }
        executor.shutdown();
    }
}