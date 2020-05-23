package aboutThread.Concurrent.Day12;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock有参方法
 * 
 * @author Aran
 */
public class Demo7 {
    private static ReentrantLock lock1 = new ReentrantLock(false);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + " : " + this.getName() + "开始获取锁！");
                // 获取锁超时时间设置为3秒，3秒内是否能获取到锁都会返回
                if (lock1.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println(System.currentTimeMillis() + " : " + this.getName() + "获取到了锁！");
                    // 获取到锁之后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + " : " + this.getName() + "未获取到锁！");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    public static void main(String[] args) {
        T t1 = new T("t1");
        T t2 = new T("t2");

        t1.start();
        t2.start();
    }
}