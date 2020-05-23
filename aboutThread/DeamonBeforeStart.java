package aboutThread;

import java.util.concurrent.TimeUnit;

public class DeamonBeforeStart {
        public static void main(String[] args){
            Thread t1 = new Thread(){
                @Override
                public void run(){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();
            t1.setDaemon(true);
        }
}