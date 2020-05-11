package aboutSynchronized;

/**
 * 同步线程
 */

 class SyncThread implements Runnable{

    private static int count;

    public SyncThread() {
        count = 0;
    }
    @Override
    public void run() {
        synchronized(this){
            for(int i = 0; i < 5 ; i++){
                try {
                    System.out.println(Thread.currentThread().getName()+":" + (count++));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount(){
        return count;
    }
     
 }

 public class SyncThreadAboutCodeblock{

    public static void main(String[] args) {
        //方式1
    //    SyncThread syncThread = new SyncThread();
    //    Thread thread1 = new Thread(syncThread,"SyncThread1");
    //    Thread thread2 = new Thread(syncThread,"SyncThread2");

    //方式二
       Thread thread1 = new Thread(new SyncThread(),"SyncThread1");
       Thread thread2 = new Thread(new SyncThread(),"SyncThread2");
 
       thread1.start();
       thread2.start();
    }
       
 }