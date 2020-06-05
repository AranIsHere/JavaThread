package aboutThread.Concurrent.Day16;

import java.util.concurrent.CountDownLatch;

public class Demo3 {
    
    public static class T extends Thread{
        //休眠时间（秒）
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
        }
    }
}