package aboutThread;

import java.util.concurrent.TimeUnit;

public class ThreadTerminate{
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            public void run(){
                System.out.println("start");
                boolean flag = true;
                while(flag){
                    ;
                }
                System.out.println("end");
            }
        };
        t1.setName("t1");
        t1.start();
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //关闭线程t1
        t1.stop();
        //输出t1的状态
        System.out.println(t1.getState());
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //输出线程t1的状态
        System.out.println(t1.getState());
    }
}