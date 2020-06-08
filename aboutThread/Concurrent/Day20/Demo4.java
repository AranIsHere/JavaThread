package aboutThread.Concurrent.Day20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        long starttime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2; 
            String taskName = "任务" + i;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                System.out.println(taskName + " - 执行完毕！");
                return j;
            });
        }
        Integer integer = invokeAny(executorService,list);
        long endtime = System.currentTimeMillis();
        System.out.println("耗时："  + (endtime - starttime) + "，执行结果" + integer);
        executorService.shutdown();
    }

    public static <T> T invokeAny(Executor e,Collection<Callable<T>> solvers) throws InterruptedException,ExecutionException{
        CompletionService<T> ecs = new ExecutorCompletionService<T>(e);
        List<Future<T>> futureList = new ArrayList<>();
        for (Callable<T> s : solvers) {
            futureList.add(ecs.submit(s));
        }
        int n = solvers.size();
        try {
            for (int i = 0; i < n; i++) {
                T r = ecs.take().get();
                if(r != null){
                    return r;
                }
            }
        }finally {
            for(Future<T> future : futureList){
                future.cancel(true);
            }
        }
        return null;
    }
}