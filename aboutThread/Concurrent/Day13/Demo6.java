package aboutThread.Concurrent.Day13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo6 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {

        @Override
        public void run() {
           lock.lock();
           try {
               System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start");
               long r = condition.awaitNanos(TimeUnit.SECONDS.toNanos(5));
               System.out.println(r);
               System.out.println(System.currentTimeMillis() + "," + this.getName() + ",end");
           } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
        
    }

    public static void main(String[] args){
        T1 t1 = new T1();

        t1.setName("t1");
        t1.start();

        
    }
}