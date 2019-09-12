package cn.wulang.CollectionDemo.JiaoJi;

import java.util.*;

/**
 * @author wulang
 * @create 2019/9/5/11:23
 */
public class JiaoJi {
    public static void main(String[] args) {
        Student s1 = new Student("小红","1");
        Student s2 = new Student("小白","2");
        Student s3 = new Student("小四","3");
        Student s4 = new Student("小三","4");
        Student s5 = new Student("老王","5");
        Student s6 = new Student("老李","6");
        Student s7 = new Student("欣欣","7");
        Student s8 = new Student("向荣","8");
        Student s9 = new Student("it民工","9");

        //田径名单
        List<Student> raceStudents = new ArrayList<>();
        raceStudents.add(s1);
        raceStudents.add(s2);
        raceStudents.add(s3);
        raceStudents.add(s6);
        raceStudents.add(s9);

        //运动会名单
        List<Student> jumpStudents = new ArrayList<>();
        jumpStudents.add(s1);
        jumpStudents.add(s6);
        jumpStudents.add(s7);
        jumpStudents.add(s8);
        jumpStudents.add(s9);
        raceStudents.retainAll(jumpStudents);
        System.out.println(Arrays.toString(raceStudents.toArray()));
    }

    public static List<Integer> getRepetition(List<Integer> list1,
                                              List<Integer> list2) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer integer : list2) {//遍历list1
            if (list1.contains(integer)) {//如果存在这个数
                result.add(integer);//放进一个list里面，这个list就是交集
            }
        }
        return result;
    }
}
class Student
{
    // 学生姓名
    private String name;
    // 学号
    private String id;

    //省略getter setter 构造器方法

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        return name == null ? false : this.id.equals(((Student) obj).getId());
    }

}