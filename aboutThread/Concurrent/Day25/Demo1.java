package aboutThread.Concurrent.Day25;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    //推送队列
    static ArrayBlockingQueue<String> pushQueue = new ArrayBlockingQueue<String>(10000);

    static{
        //启动一个线程做真实推送
        new Thread(() ->{
            while(true){
                String msg;
                try {
                    long startTime = System.currentTimeMillis();
                    //获取一条推送消息
                    msg =  pushQueue.take();
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息:%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName(), msg));           
                     }catch(InterruptedException e)
                     {
                        e.printStackTrace();
                     }
                     
                
            }
        }).start();
    }

    /**
     * 推送消息，需要发送推送消息的调用该方法，会将推送消息优先加入推送队列
     * @param msg
     * @throws InterruptedException
     */
    public static void pushMsg(String msg) throws InterruptedException{
        pushQueue.put(msg);
    }   

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 5; i++) {
            String msg = "一起来学java并发，第" + i + "天";
            // 模拟耗时
            TimeUnit.SECONDS.sleep(i);
            Demo1.pushMsg(msg);
        }
    }

    
}