package aboutThread.Concurrent.Day19;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo8 {
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() ->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " START!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," +Thread.currentThread().getName() + " END!");    
            return 10;
        });
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(1);
        result.cancel(false);
        System.out.println(result.isCancelled());
        System.out.println(result.isDone());

        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "结果，result="+ result.get());
        executorService.shutdown();

    }
}