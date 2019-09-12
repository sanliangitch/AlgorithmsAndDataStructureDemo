package cn.wulang.LinkedList;

/**
 * @author wulang
 * @create 2019/7/14/10:00
 */
public class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //新增
    public void add(HeroNode heroNode) {
        //1. 找到当前链表的最后节点
        //2. 将最后这个节点的 next 指向 新的节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //当退出 while 循环时，temp 就指向了链表的最后
        //将最后这个节点的 next 指向 新的节点
        temp.next = heroNode;
    }

    //按要求新增，内部进行排序，若有重复则禁止重复插入
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("有重复数据%d，请检查后重试", heroNode.no);
        } else {

            heroNode.next = temp.next;
            temp.next = heroNode;
            /**
             * 下面为错误写法，
             * 注意顺序问题，防止循环引用
             */
//            temp.next = heroNode;
//            heroNode.next = temp.next;
        }
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder1(HeroNode heroNode) {
    //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
    //因为单链表，因为我们找的 temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag 标志添加的编号是否存在，默认为 false
        while (true) {
            if (temp.next == null) {//说明 temp 已经在链表的最后
                break; //
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在 temp 的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的 heroNode 的编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
//判断 flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
//插入到链表中, temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示
    public void show() {
        if (head.next == null) {
            System.out.println("TnT   没数据呐");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //千万记住往后面移动
            temp = temp.next;
        }
    }

    /**
     * 修改
     */
    public void update(HeroNode heroNode){
        //判断是否为空，定义一个辅助变量，表示是否找到该节点
        if (head.next == null){
            System.out.println("链表为空呐~~~~");
            return;
        }
        //一定要为head.next，
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = heroNode.name;
            temp.nikename = heroNode.nikename;
        }else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
        }


    }
    /**
     * 删除
     */
    public void del(int no){
        boolean flag = false;
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                //链表已经遍历到最后
                break;
            }
            if (temp.next.no == no){
                flag = true;
                /**
                 * 不要忘记break
                 */
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("没找到有效数据。请输入有效数据   TuT");
        }
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
    public void del1(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while(true) {
            if(temp.next == null) { //已经到链表的最后
                break;
            }
            if(temp.next.no == no) {
//找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            temp = temp.next; //temp 后移，遍历
        }
//判断 flag
        if(flag) { //找到
//可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
    }

