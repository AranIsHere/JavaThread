package aboutThread.Concurrent.Day14;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            synchronized(lock){
                System.out.println(System.currentTimeMillis() + "," +Thread.currentThread().getName() + " start!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " end!");
            }
        });

        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        synchronized(lock){
            lock.notify();
        }
    }
}