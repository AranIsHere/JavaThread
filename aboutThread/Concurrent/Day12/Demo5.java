package aboutThread.Concurrent.Day12;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
  

    public static class T extends Thread {
        int lock;
        public T(String name, int lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if(this.lock == 1){
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                }else{
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lockInterruptibly();
                }
            } catch (Exception e) {
               System.out.println("中断标志：" + this.isInterrupted());
               e.printStackTrace();
            } finally{
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
            }
        }
        
         
        
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1",1);
        T t2 = new T("t2",2);

        t1.start();
        t2.start();

        //新增以下两行
        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();
    }
}