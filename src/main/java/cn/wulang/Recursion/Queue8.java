package cn.wulang.Recursion;

/**
 * @author wulang
 * @create 2019/7/23/21:36
 */
public class Queue8 {
    static int max = 8;
    static int[] arr = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount);
    }
    //编写一个方法，放置第n个皇后
    public  void check(int n){
        if (n == max){
            list();
            return;
        }
        for (int i = 0 ; i < max ; i++){
            //先把这个皇后n，放到该行的第n列
            arr[n] = i;
            if (conflict(n)){
                //进来表示不冲突，开始递归
                check(n+1);
            }
            //如果冲突，继续执行arr[n] = i,即将第n个皇后，将在本行后移一位
        }
    }
    //查看当我们放置n个皇后，就去检测皇后是否和前面已经摆放的冲突
    public static boolean conflict(int n){
        ++judgeCount;
        for (int i = 0; i < n ; i++){
            //说明：
            //arr[n] == arr[i] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n - i) == Math.abs(arr[n] - arr[i])  表示判断第n个皇后是否与第i个皇后是否在同一斜线
            //n = 1 时，放置在第2列，i=1在对角线，   n=1，arr[1]=1
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                    return false;
            }
        }
        return true;
    }
    //返回数组最后里面的数据
    public static void list(){
        ++count;
        for (int i = 0; i < arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
