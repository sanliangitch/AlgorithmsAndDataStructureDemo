package cn.wulang;

import cn.wulang.tearch.ProdConsumer_BlockQueueDemo;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App extends ClassLoader
{
    public static void main( String[] args ) throws Exception {


        Lock lock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(10) ;
        Class<?> aClass = Class.forName("cn.wulang.tearch.ProdConsumer_BlockQueueDemo");
        ProdConsumer_BlockQueueDemo myResource = (ProdConsumer_BlockQueueDemo) aClass.newInstance();

        System.out.println( "Hello World!" );


    }
}
