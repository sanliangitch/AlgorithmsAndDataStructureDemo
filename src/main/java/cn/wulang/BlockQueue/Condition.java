package cn.wulang.BlockQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wulang
 * @create 2019/5/20/10:43
 */
class Source{
    private boolean FLAG=true;
    private volatile AtomicInteger atomicInteger =new AtomicInteger();
    private Lock lock =new ReentrantLock();
    java.util.concurrent.locks.Condition c1 = lock.newCondition();
    java.util.concurrent.locks.Condition c2 = lock.newCondition();
    java.util.concurrent.locks.Condition c3 = lock.newCondition();


}
public class Condition {
}
