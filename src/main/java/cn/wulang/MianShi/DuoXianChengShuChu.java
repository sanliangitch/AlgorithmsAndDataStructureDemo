package cn.wulang.MianShi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wulang
 * @create 2019/8/29/16:25
 */
public class DuoXianChengShuChu {
    public static void main(String[] args) {
        String test = "I'm a programmer";
        char[] chars = test.toCharArray();
        Data data = new Data();
        new Thread(()->{
            for(int i=1;i<=chars.length/2;i++){
                try {
                    data.A(chars);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(()->{
            for(int i=1;i<=chars.length/2;i++){
                try {
                    data.B(chars);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }
}
class Data{
    private char[] test;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int i = 0;
    private int j = 1;
    private boolean flag = true;
    public void A(char[] test)throws Exception{
        lock.lock();
        try {
            if (!flag){
                condition.await();
            }
            System.out.print(test[i]);
            this.i += 2;
            flag = false;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void B(char[] test)throws Exception{
        lock.lock();
        try {
            if (flag){
                condition.await();
            }
            Thread.sleep(100);
            System.out.print(test[j]);
            this.j += 2;
            flag = true;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
