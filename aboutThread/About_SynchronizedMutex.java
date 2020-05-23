package aboutThread;
public class About_SynchronizedMutex {
    //作用于当前类的实例对象
    public synchronized void m1(){

    }
    //作用于当前累的类的实例对象
    public synchronized void m2(){

    }
    //作用域当前类的实例对象
    public void m3(){
        synchronized(this){

        }
    }

    // 作用域当前类的class对象
    public  static synchronized void m4(){

    } 

    // 作用于当前类的class对象
    public static void m5(){
        synchronized(About_SynchronizedMutex.class){

        }
    }

    public static class T extends Thread{
        About_SynchronizedMutex instance;

        public T(About_SynchronizedMutex instance){
            this.instance = instance;
        }

        @Override
        public void run(){
            super.run();
        }

    }

    public static void main(String[] args) throws InterruptedException{
        About_SynchronizedMutex instance = new About_SynchronizedMutex();
        Thread t1 = new Thread(() -> {
            instance.m1();
        });
        t1.start();
        Thread t2 = new Thread(() ->{

            instance.m2();

        });
        t2.start();
        Thread t3 = new Thread(() ->{

            instance.m3();

        });
        t3.start();
        About_SynchronizedMutex instance2 = new About_SynchronizedMutex();
        Thread t4 = new Thread(() ->{

            instance2.m4();

        });
        t4.start();
        Thread t5 = new Thread(() ->{
            About_SynchronizedMutex.m5();
        });
        t5.start();

        Thread t6 = new Thread(() ->{
            
            About_SynchronizedMutex.m5();
        });
    }
}