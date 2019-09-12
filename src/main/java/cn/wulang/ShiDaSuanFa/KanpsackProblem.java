package cn.wulang.ShiDaSuanFa;

/**
 * 背包问题
 *
 * @author wulang
 * @create 2019/8/23/10:11
 */
public class KanpsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w = {1,4,3};
        //物品的价值
        int[] val = {1500,3000,2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;
        //v【i】【j】  表示在前i个物品中装入容量为j的最大价值
        int[][] v = new int[n+1][m+1];
        //用做记录存放商品的情况
        int[][] path = new int[n+1][m+1];
        //第一行第一列为0
        for (int i = 0 ; i<v.length;i++){
            v[i][0] = 0;
        }
        for (int i = 0 ; i<v[0].length;i++){
            v[0][i] = 0;
        }

        //根据公式动态处理（不处理第一行、第一列）
        for (int i = 1 ; i < v.length ; i++){
            for (int j = 1 ; j < v[i].length ; j++){
                //如果物品的重量，大于包的重量 ---->因为是从第一行开始，数组下标是从0开始，所以减一
                if (w[i-1] > j){
                    v[i][j] = v[i-1][j];
                }else {
                    /**
                     * 注意数组下标减一
                     */
//                    v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i-1][j - w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i-1][j - w[i-1]]){
                        v[i][j] = val[i - 1] + v[i-1][j - w[i-1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++){
            for (int j = 0 ; j < v[i].length ; j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < path.length; i++){
            for (int j = 0 ; j < path[i].length ; j++){
                if (path[i][j] == 1){
                    System.out.printf("第%d个商品放入到背包\n",i);
                }
            }
        }
        System.out.println("------------------");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
