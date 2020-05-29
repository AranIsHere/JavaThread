package aboutThread.Concurrent.Day15;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    static Semaphore semaphore = new Semaphore(2);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            try {
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "," + thread.currentThread().getName() + " 获取许可！");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                semaphore.release();
                System.out.println(System.currentTimeMillis() + "," + thread.currentThread().getName() + " 释放许可！");
            }
        }

        
    }


    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            new T("t_1" + i).start();
        }
    }
}