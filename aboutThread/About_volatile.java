package aboutThread;

public class About_volatile {
    public volatile static boolean flag = true;

    public static class T1 extends Thread{
        public T1(String name){
            super(name);
        }
        @Override
        public void run(){
            System.out.println("线程" + this.getName() + " in");
            while(flag){
                ;
            }
            System.out.println("线程" + this.getName() + "停止了");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();
        //休眠1秒
        Thread.sleep(1000);
        //将flag置为false
        flag = false;
    }
}