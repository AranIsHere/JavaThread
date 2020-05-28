package aboutThread.Concurrent.Day14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Demo9 {
   
    static class BlockerDemo{

    }

   
    public static void main(String[] args){
        Thread t1 = new Thread(() ->{
            LockSupport.park();
        });  

        t1.setName("t1");
        t1.start();
        
        Thread t2 = new Thread(() ->{
            LockSupport.park(new BlockerDemo());    
        });

        t2.setName("t2");
        t2.start();



    }
}