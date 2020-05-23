package aboutThread;

import java.util.concurrent.TimeUnit;

public class AboutThreadGroup {
    
  
        public static class R1 implements Runnable{
          
            @Override
            public void run(){
                System.out.println("当前线程名："+Thread.currentThread().getName());
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
   
    public static void main(String[] args) throws InterruptedException{
        ThreadGroup tg = new ThreadGroup("threadGroup_1");
        Thread t1 = new Thread(tg,new R1(),"t1");
        Thread t2 = new Thread(tg,new R1(),"t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("活动线程数："+tg.activeCount());
        System.out.println("活动线程组："+tg.activeGroupCount());
        System.out.println("线程组名称："+tg.getName());
    }
}