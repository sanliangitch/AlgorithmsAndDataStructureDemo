package cn.wulang.BlockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MySource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MySource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myPord() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"\t生产停止");
        }
    }

    public void myConsumer() throws InterruptedException {
        String s = null;
        while (FLAG){
            s = blockingQueue.poll(2,TimeUnit.SECONDS);
            if (s==null || s.equals("")){
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒，消费退出");
                FLAG = false;
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+s+"成功");
        }
    }

    public void stop(){
        this.FLAG =false;
    }
}

public class BlockQueue {
    public static void main(String[] args) {
        MySource mySource = new MySource(new ArrayBlockingQueue<>(5));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            System.out.println();
            System.out.println();
            try{
                mySource.myPord();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try{
                mySource.myConsumer();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();
    }

}
