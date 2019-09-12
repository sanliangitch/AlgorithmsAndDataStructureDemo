package cn.wulang.LinkedList;

/**
 * @author wulang
 * @create 2019/7/15/20:48
 */
public class HeroNode2 {
    public int no;
    public String name;
    public String nikename;
    public HeroNode2 next;
    public HeroNode2 pre;
    public HeroNode2(int hNo,String hName,String hNikename){
        this.no = hNo;
        this.name = hName;
        this.nikename = hNikename;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }
}
