package aboutSynchronized;

public class SyncThreadAboutClass {
      //方法调用
      public static void main(String[] args){
        SyncThread3 syncThread1 = new SyncThread3();
        SyncThread3 syncThread2 = new SyncThread3();
        
        Thread thread1 = new Thread(syncThread1, "syncThread1");
        Thread thread2 = new Thread(syncThread2,"syncThread2");
        thread1.start();
        thread2.start();
    }
}

class SyncThread3 implements Runnable{
    private static int count;
    
    public SyncThread3(){
        count = 0;
    }

    public static void method(){
        synchronized(SyncThread3.class){
            for(int i = 0; i < 5; i++){
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        method();

    }

    
}