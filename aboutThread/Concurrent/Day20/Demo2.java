 package aboutThread.Concurrent.Day20;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo2 {
   static class GoodsModel {
       //商品名称
       String name;
       //购物开始时间
       long starttime;
       //送到的时间
       long endtime;

       public GoodsModel(String name,long starttime,long endtime){
           this.name = name;
           this.starttime = starttime;
           this.endtime = endtime;
       }

       @Override
       public String toString() {
        return name + "，下单时间[" + this.starttime + "," + endtime + "]，耗时:" + (this.endtime - this.starttime);
       }
   } 

   /**
    * 将商品搬上楼
    * @param goodsModel
    * @throws InterruptedException
    */
   static void moveUp(GoodsModel goodsModel) throws InterruptedException{
       //休眠5秒，模拟上楼耗时
       TimeUnit.SECONDS.sleep(5);
       System.out.println("将商品搬上楼，商品信息：" + goodsModel);
   }


   static Callable<GoodsModel> buyGoods(String name,long costTime){
       return () -> {
           long startTime = System.currentTimeMillis();
           System.out.println(startTime + "购买【" + name + "】下单!");
           //模拟送货耗时
           try {
               TimeUnit.SECONDS.sleep(costTime);
           } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "-" + name + "送到了！");
            return new GoodsModel(name, startTime, endTime);
       };
   }

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        long st = System.currentTimeMillis();
        System.out.println("开始购物！");
        ExecutorService executor = Executors.newFixedThreadPool(5);
         
        //创建ExecutorCompletionService对象
        ExecutorCompletionService<GoodsModel> executorCompletionService = new ExecutorCompletionService<>(executor);
        //异步下单买冰箱
        executorCompletionService.submit(buyGoods("冰箱", 5));
        //异步下单买洗衣机
        executorCompletionService.submit(buyGoods("洗衣机", 2));
        executor.shutdown();

        int goodsCount = 2;
        for (int i = 0; i < goodsCount; i++) {
            //可以先获取到最先到的商品
            GoodsModel goodsModel = executorCompletionService.take().get();

            //将最先到的商品送上楼
            moveUp(goodsModel);
        }

        long et = System.currentTimeMillis();
        System.out.println(et + "货物已送到家啦，哈哈哈哈！");
        System.out.println("总耗时：" + (et - st));
    }

}