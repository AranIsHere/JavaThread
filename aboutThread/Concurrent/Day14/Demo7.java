package aboutThread.Concurrent.Day14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo7 {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "被唤醒！");
        });

        t1.setName("t1");
        t1.start();

        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t1);
        System.out.println(System.currentTimeMillis()+", LockSupport.unpark(t1);执行完毕！");
    }
}