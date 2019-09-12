package cn.wulang.stack;

/**
 * 扫描出数字直接入数栈，记得扫描的是char   是ASCII码，要减48
 *
 * 要考虑如果不是个位数的情况，明天需要修改----已完成
 * @author wulang
 * @create 2019/7/19/6:37
 */
public class Calculator {
    public static void main(String[] args) {
        String experssion = "70*2-5+1-6";
        //定义需要的相关变量
        //数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        //指针，num1，num2，oper--操作符，res--每次运算的结果，ch--将每次的扫描结果保存到ch，keepNum--用于拼接多位数
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (true){
            //循环拿出表达式里面的值
            ch = experssion.substring(index, index + 1).charAt(0);
            //判断是数还是符号
            //如果是运算符
            if(operStack.isOper(ch)){
                //判断符号栈里面是否为空，为空直接加入
                //不为空，拿出一个符号，拿出数栈里面两个值，之后运算，把结果压入数栈
                if (!operStack.empty()){
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {
                //为数
//                numStack.push(ch - 48);
                //处理多位数
                keepNum += ch;
                //如果是最后一位直接入栈
                if (index == experssion.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一位是不是数字。如果是，继续扫描，不是入数栈
                    //只是看不是index++
                    if (operStack.isOper(experssion.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        //切记让keepNum清空
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= experssion.length()){
                break;
            }
        }
        //表示扫描完毕，顺序从数栈和符号栈里面拿出值
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.empty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //计算完成，返回最后的结果
        int rex2 = numStack.pop();
        System.out.println("最后的结果为："+ rex2);
    }
}

class ArrayStack2{
    //栈的最大大小
    private int maxSize;
    //数组，模拟栈，数据放在该数据中
    private int[] arr;
    //栈底
    private int top = -1;
    //构造器
    public ArrayStack2(int maxSize){
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

    //返回运算符的优先级，优先级由程序员自己决定，优先级用数字表示--priority
    //数字越大，优先级越小
    public int priority(int open){
        if (open == '*' || open == '/'){
            return 1;
        }
        if (open == '+' || open == '-'){
            return 0;
        } else {
            return -1;
        }
    }
    //判断是不是一个运算符--isOper
    public boolean isOper(char val){
        return val == '*'||val  == '/' || val == '+' || val == '-';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
             default:
                 break;
        }
        return res;
    }
    //可以返回当前栈顶的值，只是查看--peek
    public int peek(){
        return arr[top];
    }
}