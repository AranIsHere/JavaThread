package aboutThread.Concurrent.Day17;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Demo5 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    //规则是否已重现
    public static boolean rules = false;

    public static class T extends Thread{
        int sleep;

        public T(String name,int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run(){
            long startTime = 0,endTime = 0;
            try {
                //模拟休眠
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                //调用await()时，当前线程会被阻塞，需要等待其他员工都达到await了才能继续
                System.out.println(this.getName() + "到了！");
                if(!rules){
                    if(this.getName().equals("员工1")){
                        cyclicBarrier.await(5, TimeUnit.SECONDS);
                    }else{
                        cyclicBarrier.await();
                    }
                }else{
                    cyclicBarrier.await();
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            } catch (BrokenBarrierException e){
                e.printStackTrace();
            } catch(TimeoutException e){
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName() + ", sleep:"+this.sleep + " 等待了" + (endTime - startTime) +"(ms),开始吃饭了！");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        for (int i = 1; i <= 10; i++) {
            T t = new T("员工"+ i,i);
            t.start();
        }

        TimeUnit.SECONDS.sleep(15);
        cyclicBarrier.reset();
        rules = true;
        System.out.println("++++++++++Obey the Rules++++++++++");
        //再来一次
        for (int i = 1; i <= 10; i++) {
            T t = new T("员工"+ i,i);
            t.start();
        }
    }
}