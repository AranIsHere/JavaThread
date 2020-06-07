package aboutThread.Concurrent.Day19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo6 {
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis()  + "," + Thread.currentThread().getName() + " end!");
            return 10;
        });
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "");
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 结果" + result.get());
    }
}