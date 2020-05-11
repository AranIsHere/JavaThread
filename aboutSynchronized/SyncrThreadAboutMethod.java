package aboutSynchronized;

public class SyncrThreadAboutMethod {
    public static void main(String[] args){

     
        //方式1
       SyncThread1 syncThread = new SyncThread1();
       Thread thread1 = new Thread(syncThread,"SyncThread1");
       Thread thread2 = new Thread(syncThread,"SyncThread2");

  
 
       thread1.start();
       thread2.start();
   
    }
}


class SyncThread1 implements Runnable{

    private static int count;

    public SyncThread1() {
        count = 0;
    }
    @Override
    public synchronized void run() {
        
            for(int i = 0; i < 5 ; i++){
                try {
                    System.out.println(Thread.currentThread().getName()+":" + (count++));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
       
    }

    public int getCount(){
        return count;
    }
     
 }
