package cn.wulang.LinkedList;

/**
 * @author wulang
 * @create 2019/7/14/9:54
 */
public class HeroNode {
    public int no;
    public String name;
    public String nikename;
    public HeroNode next;
    public HeroNode(int hNo,String hName,String hNikename){
        this.no = hNo;
        this.name = hName;
        this.nikename = hNikename;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }
}
