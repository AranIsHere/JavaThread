package aboutThread.Concurrent.Day16;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo5 {
    public static class T extends Thread{
        //跑步耗时
        int runCostSeconds;
        CountDownLatch commanderCd;
        CountDownLatch countDownLatch;

        public T(String name,int runCostSeconds,CountDownLatch commanderCd,CountDownLatch countDownLatch){
            super(name);
            this.runCostSeconds = runCostSeconds;
            this.commanderCd = commanderCd;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            //等待指令枪响
            try {
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ",开始跑！");
            try {
                //模拟耗时操作，休眠runCostSeconds秒
                TimeUnit.SECONDS.sleep(this.runCostSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                countDownLatch.countDown();
            }

            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ",跑步结束，耗时：" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException{
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ", 线程start!");
        CountDownLatch commanderCd = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        long startTime = System.currentTimeMillis();
        T t1 = new T("小张",2,commanderCd,countDownLatch);
        t1.start();

        T t2 = new T("小李",5,commanderCd,countDownLatch);
        t2.start();

        T t3 = new T("路人甲",10,commanderCd,countDownLatch);
        t3.start();


        // 主线程休眠5秒
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + ",枪响了，大家开始跑！");
        commanderCd.countDown();

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",所有人都跑完了！耗时：" + (endTime - startTime));
    }
}