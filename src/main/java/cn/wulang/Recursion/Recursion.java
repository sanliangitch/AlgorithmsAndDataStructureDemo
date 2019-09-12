package cn.wulang.Recursion;

/**
 * @author wulang
 * @create 2019/7/21/22:30
 */
public class Recursion {
    public static void main(String[] args) {
        test(6);
        int factorial = factorial(4);
        System.out.println(factorial);
    }
    //阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }}
    //输出什么?
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

}
