package aboutThread;

import java.util.concurrent.TimeUnit;

public class InterruptedBy_Variable {
    public volatile static boolean exit = true;

    public static class T extends Thread{
        @Override
        public void run(){
            while(true){
                //循环处理业务
                if(exit){
                    break;
                }
            }
        }
    }

    public static void setExit(){
        exit = true;
    }

    public static void main(String[] args) throws InterruptedException{
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(1);
        setExit();
    }
}