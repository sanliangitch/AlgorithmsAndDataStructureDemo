package cn.wulang.LinkedList;

import java.util.Stack;

/**
 * @author wulang
 * @create 2019/7/14/10:16
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(4, "林冲123", "豹子头123");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//         singleLinkedList.add(hero1);
//         singleLinkedList.add(hero4);
//         singleLinkedList.add(hero2);
//         singleLinkedList.add(hero3);
//
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

//         singleLinkedList.addByOrder1(hero1);
//         singleLinkedList.addByOrder1(hero4);
//         singleLinkedList.addByOrder1(hero2);
//         singleLinkedList.addByOrder1(hero3);

         singleLinkedList.show();
        System.out.println();

        singleLinkedList.update(hero5);
        singleLinkedList.show();
        System.out.println();
        singleLinkedList.del(4);
        singleLinkedList.show();
        System.out.println("链表里面的有效个数"+ getLength(singleLinkedList.getHead()));
        System.out.println("倒数第几个为："+findLastIndexNode(singleLinkedList.getHead(),1).toString());

        System.out.println("反转之后为");
        reversetList(singleLinkedList.getHead());
        reversePrint(singleLinkedList.getHead());

    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    static public int getLength(HeroNode node){
        if (node.next == null){
            return 0;
        }
        int count = 0;
        HeroNode temp = node;
        while (temp.next != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 查找单链表中的倒数第 k 个结点 【新浪面试题】
     */
    //思路
    //1. 编写一个方法，接收 head 节点，同时接收一个 index
    //2. index 表示是倒数第 index 个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回 nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null){
            return null;
        }
        int length = getLength(head);
        if (index < 0 || index > length){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0 ; i < length - index; i++){
            temp = temp.next;
        }
        return temp;
    }
    /**
     * 将单链表反转
     */
    public static void reversetList(HeroNode head) {
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助的指针（变量），用来遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点【cur】的下一个节点
        HeroNode next = null;
        //重新定义一个新的链表头结点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while (cur!=null){
            //暂时保存下一个节点，因为后面要用到
            next = cur.next;
            //将cur的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            //将cur连接到新链表上
            reverseHead.next = cur;
            //让cur后移
            cur = next;
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    //单链表的逆序打印代码
    public static void reversePrint(HeroNode head) {
        if (head.next == null){
            return;
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            heroNodes.push(cur);
            cur = cur.next;
        }
        while (heroNodes.size() > 0 ){
            System.out.println("逆序为："+heroNodes.pop());
        }
    }
}
