package aboutThread.Concurrent.Day14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo6 {
    public static void main(String[] args) throws InterruptedException{
        Thread t1  = new Thread(() ->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒！");
        });
        t1.setName("t1");
        t1.start();

        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        LockSupport.unpark(t1);

        System.out.println(System.currentTimeMillis() + ", LockSupport().unpark();执行完毕！" );
    }
}