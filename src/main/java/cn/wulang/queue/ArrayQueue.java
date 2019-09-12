package cn.wulang.queue;

/**
 * 编写一个arrayQueue类
 *
 * @author wulang
 * @create 2019/7/13/11:14
 */
public class ArrayQueue {
    //最大容量
    private int maxSize;
    //指向头
    private int front;
    //指向尾
    private int rear;
    //数组大小
    private int[] arr;


    //创建条件构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        front = -1;
        rear = -1;
        arr = new int[arrMaxSize];
    }
    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize - 1 ;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        if (rear == maxSize - 1){
            System.out.println("队列已经满呐~~");
            return;
        }
        rear++;
        arr[rear] = n;
    }
    //获取数据数据，出队列
    public int getQueue(){
        if (rear == front){
            throw new RuntimeException("队列没数据呐~~");
        }
        front++;
        return arr[front];
    }
    //显示队列的所有数据
    public void showQueue(){
        if (rear == front){
            System.out.println("哭，队列没数据  TnT");
            return;
        }
        for (int i = 0; i < arr.length ; i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front+1];
    }
}
