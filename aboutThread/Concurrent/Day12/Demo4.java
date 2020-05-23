package aboutThread.Concurrent.Day12;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {

    //true:公平 false:非公平
    private static ReentrantLock fairLock = new ReentrantLock(false);

    public static class T extends Thread{
        public T(String name){
            super(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 5; i++) {
                fairLock.lock();
                try{
                    Thread.sleep(100);
                    System.out.println(this.getName() + " 获得锁！");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    fairLock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException{
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");
        
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
 }