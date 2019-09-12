package cn.wulang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wulang
 * @create 2019/7/10/20:57
 */
public class Lang {
    public static void main(String[] args) {
        long l1 = (long)65;
        long l2 = 65;
        long l3 = Long.valueOf(65);
        Long l4= new Long(65);
        System.out.println(l1 == l2);
        System.out.println();

        char[] a = {'A','B','C','D'};
        String s = Arrays.toString(a);
        System.out.println(s);
        point point1 = new point("张三",19,91);
        point point2 = new point("李四",20,91);
        point point3 = new point("王五",20,93);
        point point4 = new point("赵六",18,91);
        point point5 = new point("嘻嘻",19,95);
        point point6 = new point("呵呵",19,96);
        ArrayList<point> list = new ArrayList<>();
        list.add(point1);
        list.add(point6);
        list.add(point4);
        list.add(point5);
        list.add(point3);
        list.add(point2);
        for (point p : list){
            System.out.println(p.toString());
        }
        list.sort(new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if (o1.score < o2.score){
                    return -1;
                }
                return 0;
            }
        });
        list.sort(new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if (o1.age < o2.age){
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("排序后");
        for (point p : list){
            System.out.println(p.toString());
        }
    }
}
class point{
    String name;
    int age;
    int score;
    public point(String name,int age,int score){
        this.name = name;
        this.age = age;
        this.score = score;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "point{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}