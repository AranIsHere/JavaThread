package aboutThread.Concurrent.Day16;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Demo6 {
   
    // 并行线程数
    public static final int POOL_SIZE;

    static{
        POOL_SIZE = Integer.max(Runtime.getRuntime().availableProcessors(), 5);
    }


    /**
     * 并行处理，并等待结束
     * 
     * @param <T>
     * @param taskList 任务列表
     * @param consumer 消费者
     * @throws InterruptedException
     */
    public static <T> void dispose(List<T> taskList,Consumer<T> consumer) throws InterruptedException{
        dispose(true,POOL_SIZE,taskList, consumer);
    }



    public static <T> void dispose(boolean moreThread, int poolSize,List<T> taskList, Consumer<T> consumer) throws InterruptedException{
       if(CollectionUtils.isEmpty(taskList)){
           return;
       } 
    }
}