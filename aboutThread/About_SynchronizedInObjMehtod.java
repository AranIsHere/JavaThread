package aboutThread;

public class About_SynchronizedInObjMehtod {
    int num = 0; 
    public synchronized void add(){
        num++;
    }

    public static class TClass extends Thread{
        private About_SynchronizedInObjMehtod curObj;
        
        public TClass(About_SynchronizedInObjMehtod curObj)
        {
            this.curObj = curObj;
        }

        @Override
        public void run(){
            for(var i = 0; i < 10000;i++){
                this.curObj.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        About_SynchronizedInObjMehtod curObj = new About_SynchronizedInObjMehtod();

        TClass t1 = new TClass(curObj);
        TClass t2 = new TClass(curObj);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(curObj.num);
    }
}