package aboutThread.Concurrent.Day16;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    public static class T extends Thread{
        //休眠时间
        int sleepSeconds;
        CountDownLatch countDownLatch;

        public T(String name,int sleepSeconds,CountDownLatch countDownLatch){
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ", 开始处理！");
            try {
                //模拟耗时操作，休眠sleepSeconds秒
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            } finally{
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ", 处理完毕，耗时" + (endTime - startTime));

        }
    }


    public static void main(String[] args) throws InterruptedException{
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 线程start!");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        long startTime = System.currentTimeMillis();
        T t1 = new T("解析Sheet1线程",2,countDownLatch);
        t1.start();

        T t2 = new T("解析Sheet2线程",5,countDownLatch);
        t2.start();

        countDownLatch.await();

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 线程end");
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时："+ (endTime - startTime));
    }
}