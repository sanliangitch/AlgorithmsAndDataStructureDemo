package cn.wulang.BlockQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * A打印5次，B打印10次，C打印15次
 * 紧接着
 * A打印5次，B打印10次，C打印15次
 * 。。。。。
 * 打印10轮
 *
 * @author wulang
 * @create 2019/5/20/11:24
 */
class XiaoFei {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("-" + i);
            }
            num = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("--" + i);
            }
            num = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println("---" + i);
            }
            num = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }

    }
}

public class JingZhunXiaoFei {
    public static void main(String[] args) {
        XiaoFei xiaoFei = new XiaoFei();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    xiaoFei.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    xiaoFei.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    xiaoFei.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }

    //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//    ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
//    ExecutorService threadPool = Executors.newFixedThreadPool(1);//一池1个线程
    //ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程
}
