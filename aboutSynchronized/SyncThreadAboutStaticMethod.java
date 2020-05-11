package aboutSynchronized;

public class SyncThreadAboutStaticMethod {
    //方法调用
    public static void main(String[] args){
        SyncThread2 syncThread1 = new SyncThread2();
        SyncThread2 syncThread2 = new SyncThread2();
        
        Thread thread1 = new Thread(syncThread1, "syncThread1");
        Thread thread2 = new Thread(syncThread2,"syncThread2");
        thread1.start();
        thread2.start();
    }
}

/**
 * 同步线程
 */
class SyncThread2 implements Runnable{

    private static int count;

    public SyncThread2(){
        count = 0;
    }

    public synchronized static void method(){  
        for(var i = 0; i < 5; i++){
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }            
        }
    }


    @Override
    public void run() {
       method();
    }

}