package aboutThread;

import java.util.concurrent.TimeUnit;

public class InterruptedIn_blocked {
    public static class T extends Thread{
        @Override
        public void run(){
            while(true){
                //循环处理业务
                // 以下模拟阻塞代码
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
                if(this.isInterrupted()){
                    break;
                }
            }
           
        }
    }
    public static void main(String[] args) throws InterruptedException{
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
    }
}