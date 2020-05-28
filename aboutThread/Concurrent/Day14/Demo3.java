package aboutThread.Concurrent.Day14;

import java.util.concurrent.TimeUnit;

public class Demo3 {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start！");
                try {
                    // 休眠3秒
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "被唤醒！");
            }
         });
        t1.setName("t1");
        t1.start();
        // 休眠1秒后唤醒lock对象上等待的线程
        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            lock.notify();
        }
        System.out.println("lock.notify()执行完毕");
    }
}