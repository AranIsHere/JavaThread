package aboutThread.Concurrent.Day25;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    /**
     * 推送消息封装
     */
    static class Msg implements Comparable<Msg> {
        // 优先级，越小优先级越高
        private int priority;
        // 推送的信息
        private String msg;

        public Msg(int priority, String msg) {
            this.priority = priority;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return  "Msg{ " + "priority=" + priority + ",msg = '" + msg + "'}";
        }

        @Override
        public int compareTo(Msg o) {
            return Integer.compare(this.priority, o.priority);
        }

    }

    // 推送队列
    static PriorityBlockingQueue<Msg> pushQueue = new PriorityBlockingQueue<Msg>();

    static {
        // 启动一个线程做真实推送
        new Thread(() -> {
            while(true){
            Msg msg;
            try {
                long startTime = System.currentTimeMillis();
                // 获取一条推送信息，此方法会进行阻塞，直到返回结果
                msg = pushQueue.take();
                // 模拟推送耗时
                TimeUnit.MILLISECONDS.sleep(100);
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("[%s,%s,take耗时：%s],%s,发送消息：%s", startTime, endTime,
                        (endTime - startTime), Thread.currentThread().getName(), msg));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }).start();
    }

    public static void pushMsg(int priority,String msg) throws InterruptedException{
        pushQueue.put(new Msg(priority,msg));
    }

    public static void main(String[] args) throws InterruptedException{
        for (int i = 5; i >= 1; i--) {
            String msg = "一起学习java高并发，第" + i + "天";
            Demo2.pushMsg(i, msg);    
        }

    }
}