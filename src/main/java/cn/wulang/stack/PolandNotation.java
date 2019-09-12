package cn.wulang.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wulang
 * @create 2019/7/21/11:29
 */
public class PolandNotation {
    public static void main(String[] args) {
        /*
        String expression = "3 4 + 5 * 6 -";
        List<String> list = getListString(expression);
        int i = calculate(list);
        System.out.println(i);
        */
        String expression = "10+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpreesionList);
        System.out.printf("expression=%d", calculate(suffixExpreesionList));
    }

    //将中綴的list转换为后缀的list
    private static List<String> parseSuffixExpreesionList(List<String> infixExpressionList) {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (String item : infixExpressionList) {
            //如果是个数
            if (item.matches("\\d+")){
                list.add(item);
            }else if (item.equals("(")){
                stack.push(item);
            }else if (item.equals(")")){
                //如果是“）”，则依次弹出stack栈顶的运算符，并压入list，直到遇到"("为止，此时将这一对括号丢弃
                while (!stack.peek().equals("(")){
                    list.add(stack.pop());
                }
                stack.pop();
            }else {
                //当item中的优先级，小于栈顶的优先级，将栈顶的运算符弹入到list
                //之后再次比较栈顶的运算符
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)){
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }
        while (stack.size()!=0){
            list.add(stack.pop());
        }
        return list;
    }

    //将中綴表达式转成对应的list
    private static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0;
        char ch = ' ';
        String str;
        while (index < expression.length()){
            //判断是否为数字
            if ((ch = expression.charAt(index)) < 48 || (ch = expression.charAt(index)) > 57){
                //进来表示非数字
                list.add("" + ch);
                index++;
            }else {
                //进来表示为数字
                str = "";
                while (index < expression.length() &&
                        (ch = expression.charAt(index)) >= 48
                        && (ch = expression.charAt(index)) <= 57){
                    str += ch;
                    index++;
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String>  getListString(String expression){
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt((String) stack.pop());
                int num1 = Integer.parseInt((String) stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else {
                    if (!item.equals("/")) {
                        throw new RuntimeException("运算符有误");
                    }
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    Operation() {
    }

    public static int getValue(String operation) {
        int result = 0;
        switch(operation) {
            case "*":
                if (operation.equals("*")) {
                    result = MUL;
                    return result;
                }
                break;
            case "+":
                if (operation.equals("+")) {
                    result = ADD;
                    return result;
                }
                break;
            case "-":
                if (operation.equals("-")) {
                    result = SUB;
                    return result;
                }
                break;
            case "/":
                if (operation.equals("/")) {
                    result = DIV;
                    return result;
                }
        }

        System.out.println("不存在该运算符" + operation);
        return result;
    }
}