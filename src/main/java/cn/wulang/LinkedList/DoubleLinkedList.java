package cn.wulang.LinkedList;

/**
 * 双向链表
 *
 * @author wulang
 * @create 2019/7/15/20:50
 */
public class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void show() {
        if (head.next == null) {
            System.out.println("TnT   没数据呐");
            return;
        }
        //头结点不能动，用一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //千万记住往后面移动
            temp = temp.next;
        }
    }

    //新增
    public void add(HeroNode2 heroNode2) {
        //1. 找到当前链表的最后节点
        //2. 将最后这个节点的 next 指向 新的节点
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //当退出 while 循环时，temp 就指向了链表的最后
        //将最后这个节点的 next 指向 新的节点
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    /**
     * 修改
     */
    public void update(HeroNode2 heroNode2){
        //判断是否为空，定义一个辅助变量，表示是否找到该节点
        if (head.next == null){
            System.out.println("链表为空呐~~~~");
            return;
        }
        //一定要为head.next，
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == heroNode2.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = heroNode2.name;
            temp.nikename = heroNode2.nikename;
        }else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode2.no);
        }
    }

    /**
     * 删除
     */
    public void del(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        //单链表删除时希望的是找到待删除节点的前一个节点  HeroNode temp = head；
        //双向链表可以自我删除，所以直接指向自己    HeroNode2 temp = head.next;
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){
                //链表已经遍历到最后
                break;
            }
            //指向自己，所以这里对比单链表也有修改
            if (temp.no == no){
                flag = true;
                /**
                 * 不要忘记break
                 */
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //注意对比单链表
            temp.pre.next = temp.next;
            //这里的代码有安全隐患？
            //如果是最后一个节点   temp.next 就为空
            //如果是最后一个节点，就不需要执行下面的一句话，否则会出现控制住
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
//            temp.next = temp.next.next;
        }else {
            System.out.println("没找到有效数据。请输入有效数据   TuT");
        }
    }
}
