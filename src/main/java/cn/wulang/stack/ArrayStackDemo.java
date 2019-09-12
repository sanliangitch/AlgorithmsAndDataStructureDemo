package cn.wulang.stack;

import java.util.Scanner;

/**
 * @author wulang
 * @create 2019/7/17/7:23
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key.hashCode()) {
                case 111185:
                    if (key.equals("pop")) {
                        try {
                            int res = stack.pop();
                            System.out.printf("出栈的数据是 %d\n", res);
                        } catch (Exception var8) {
                            System.out.println(var8.getMessage());
                        }
                    }
                    break;
                case 3127582:
                    if (key.equals("exit")) {
                        scanner.close();
                        loop = false;
                    }
                    break;
                case 3452698:
                    if (key.equals("push")) {
                        System.out.println("请输入一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                    }
                    break;
                case 3529469:
                    if (key.equals("show")) {
                        stack.list();
                    }
            }
        }
        System.out.println("程序退出~~~");
    }
}
class ArrayStack{
    //栈的最大大小
    private int maxSize;
    //数组，模拟栈，数据放在该数据中
    private int[] arr;
    //栈底
    private int top = -1;
    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //栈满
    public boolean full(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean empty(){
        return top == -1;
    }
    //入栈-push
    public void push(int data){
        if (full()){
            System.out.println("已经满呐  TnT");
            return;
        }
        top++;
        arr[top] = data;
    }
    //出栈-pop
    public int pop(){
        if (empty()){
//            System.out.println("没数据呐  TnT");
            throw new RuntimeException("没数据呐  TnT");
        }
        int value = arr[top];
//        System.out.println("出栈数为："+ arr[top]);
        top--;
        return value;
    }
    //显示-list，需要从栈顶显示数据
    public void list(){
        if (empty()){
            System.out.println("没有数据呐");
            return;
        }
        for (int i = 0;i<maxSize-1;i++){
            if (top == 0){
                System.out.println(arr[top]);
                return;
            }
            System.out.println(arr[top]);
            top--;
        }
    }
}