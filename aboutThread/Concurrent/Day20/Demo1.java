package aboutThread.Concurrent.Day20;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    static class GoodsModel {
        // 商品名称
        String name;
        // 购物开始时间
        long starttime;
        // 送到的时间
        long endtime;

        public GoodsModel(String name, long starttime, long endtime) {
            this.name = name;
            this.starttime = starttime;
            this.endtime = endtime;
        }

        @Override
        public String toString() {
            return name + "，下单时间[" + this.starttime + "," + this.endtime + "],耗时：" + (this.endtime - this.starttime);
        }

    }

    /**
     * 将商品搬上楼
     * 
     * @param goodsModel
     * @throws InterruptedException
     */
    static void moveUp(GoodsModel goodsModel) throws InterruptedException {
        // 休眠5秒
        TimeUnit.SECONDS.sleep(5);
        System.out.println("将商品搬上楼，商品信息：" + goodsModel);
    }

    /**
     * 
     * @param name
     * @param costTime
     * @return
     */
    static Callable<GoodsModel> buyGoods(String name, long costTime) {
        return () -> {
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "购买" + name + "下单！");
            // 模拟送货耗时
            try {
                TimeUnit.SECONDS.sleep(costTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(startTime + "," + name + "-送到了!");
            return new GoodsModel(name, startTime, endTime);
        };
    }

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        long st = System.currentTimeMillis();
        System.out.println(st + ", 开始购物！");

        // 创建一个线程池，用来异步下单
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 异步下单购买冰箱
        Future<GoodsModel> bxFuture = executor.submit(buyGoods("冰箱", 5));
        //异步下单购买洗衣机
        Future<GoodsModel> xyjFuture = executor.submit(buyGoods("洗衣机",2));

        //关闭线程池
        executor.shutdown();

          //等待洗衣机
          GoodsModel xyjGoodsModel = xyjFuture.get();
          //将洗衣机搬上楼
          moveUp(xyjGoodsModel);
          
        //等待冰箱到
        GoodsModel bxGoodsModel = bxFuture.get();
        //将冰箱搬上楼
        moveUp(bxGoodsModel);
        
      

        long et = System.currentTimeMillis();
        System.out.println(et + "货物已经送到家啦，哈哈哈！");
        System.out.println("总耗时：" + (et - st));
    }
}