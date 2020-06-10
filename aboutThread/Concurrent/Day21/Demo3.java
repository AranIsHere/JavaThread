package aboutThread.Concurrent.Day21;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;



public class Demo3 {
    
    //访问次数
    volatile static int count = 0;

    // 模拟访问一次
    public static void request() throws InterruptedException{
        //模拟耗时5秒
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount;
        do{
            expectCount = getCount();
        } while(!compareAndSwap(expectCount,expectCount + 1));
    }

    /**
     * 
     * @param expectCount 期望count的值
     * @param i 需要给count赋的值
     * @return
     */
    private static boolean compareAndSwap(int expectCount, int i) {
        //判断count当前值是否和期望的expectCount一致，如果一样将newCount赋值给count
        if(getCount() == expectCount){
            count = i;
            return true;
        }
        return false;
    }
    /**
     * 获取count当前值
     * @return
     */
    private static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for(int j = 0;j < 10; j++){
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.err.println(Thread.currentThread().getName() + ",耗时：" + (endTime - startTime) + ",count = " + count);
    }
}