package cn.wulang.queue;

/**
 * 环形数组当队列
 * 默认空出一个位置
 *
 * @author wulang
 * @create 2019/7/13/17:48
 */
public class CircleArrayQueue {
    //最大容量
    private int maxSize;
    //指向头
    private int front;
    //指向尾
    private int rear;
    //数组大小
    private int[] arr;

    //创建条件构造器
    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        //int 默认为0
//        front = 0;
//        rear = 0;
    }

    //判断队列是否满了
    public boolean isFull(){
        return (rear + 1) % maxSize == front ;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear  == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已经满呐~~");
            return;
        }
        arr[rear] = n;
        rear++;
    }

    //获取数据数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列没数据呐~~");
        }
        int temp = arr[front];
        front++;
        return temp;
    }

    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("哭，队列没数据  TnT");
            return;
        }
        for (int i = front; i < front +  size(); i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //数组中有效数组的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}
