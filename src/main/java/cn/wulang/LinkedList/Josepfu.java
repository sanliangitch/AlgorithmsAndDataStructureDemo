package cn.wulang.LinkedList;

/**
 * @author wulang
 * @create 2019/7/15/21:53
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}
//环形链表
class CircleSingleLinkedList{
    //两个辅助变量
    Boy first = null;
    //增加
    public void addBoy(int num){
        if (num < 1){
            System.out.println("传入参数有误！请重新输入");
            return;
        }
        Boy curBoy = null;
        for (int i = 1 ; i <= num;i++){
            Boy boy = new Boy(i);
            //考虑是第一个小孩的情况，自己指向自己
            if (i == 1){
                first = boy;
                //构成环
                first.setNext(first);
                //让辅助指针指向第一个，方便后面使用
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    //显示
    public void show(){
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空 TnT");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩编号为第%d号\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
    //根据用户输入，计算出小孩出圈顺序

    /**
     *
     * @param startNo  表示从第几个孩子开始数数
     * @param countNo  表示数几下
     * @param nums      表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo,int countNo,int nums){
        if(startNo > nums || first == null || startNo < 1){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //补充： 小孩报数前，先让 first 和  helper 移动 k - 1次
        while (true){
            if (first.getNo() == startNo){
                break;
            }
            first = first.getNext();
        }
        //1.  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点.
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //2.  当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次
        //只是让指针到达要开始报数的位子，还没开始正式报数
        if (startNo != 1){
            for (int m = 1; m < countNo ; m++){
                first = first.getNext();
                helper = helper.getNext();
            }
        }
        //开始正式报数
        //3.  这时就可以将first 指向的小孩节点 出圈
        while (true){
            if (/*first.getNext()*/ helper == first){
                System.out.print(first.getNo());
                break;
            }
            for (int i = 0; i < countNo-1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo());
            first = first.getNext();
            helper.setNext(first);
//            first.setNext(first.getNext());
//            helper.setNext(first);
        }
    }
}

class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
