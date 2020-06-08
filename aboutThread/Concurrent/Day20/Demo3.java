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
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Demo3 {
    public static void main(String[] args) throws ExecutionException,InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i> 0; i--) {
            int j = i * 2;
            list.add(() ->{
                TimeUnit.SECONDS.sleep(j);
                return j;
            });
        }
        solve(executorService,list,a ->{
            System.out.println(System.currentTimeMillis() + ":" + a);
        });
        executorService.shutdown();
       
    }

    public static <T> void solve(Executor e,Collection<Callable<T>> solvers, Consumer<T> use) throws ExecutionException,InterruptedException{
        CompletionService<T> ecs = new ExecutorCompletionService<T>(e);
        for(Callable<T> s : solvers){
            ecs.submit(s);
        }
        int n = solvers.size();
        for (int i = 0; i < n; i++) {
            T r = ecs.take().get();
            if(r != null){
                use.accept(r);
            }
        }
    }
}