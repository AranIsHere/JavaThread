package aboutThread.Concurrent.Day13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {

        @Override
        public void run() {
             lock.lock();
             try {
                 condition.await();
             } catch (InterruptedException e) {
                  System.out.println("中断标志："+ this.isInterrupted());
                  e.printStackTrace();
             }finally{
                 lock.unlock();
             }
        }
        
    }

    public static void main(String[] args) throws InterruptedException{
        T1  t1= new T1();
        t1.setName("t1");

        t1.start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println("1. t1的中断标志："+t1.isInterrupted());
        t1.interrupt();
        System.out.println("2. t1的中断标志："+t1.isInterrupted());
    }
}