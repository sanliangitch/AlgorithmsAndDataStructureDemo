package cn.wulang.ShiDaSuanFa;

import java.util.Arrays;

/**
 * @author wulang
 * @create 2019/8/30/21:58
 */
public class FloydAorithm {
    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        FGraph fGraph = new FGraph(vertex.length, matrix, vertex);
        fGraph.show();
    }
}

class FGraph{
    private char[] vertex;
    //保存，从各个顶点出发到其他顶点的距离，最后的结果也保存在该数组
    private int[][] dis;
    //保持到达目标顶点的前驱顶点，动态变化
    private int[][] pre;

    /**
     *
     * @param lenght  大小
     * @param matrix  邻接矩阵
     * @param vertex  顶点数组
     */
    public FGraph(int lenght,int[][] matrix,char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[lenght][lenght];
        //对 pre 初始化，存放的是前驱顶点的下标
        for (int i = 0 ; i < lenght; i++){
            Arrays.fill(pre[i],i);
        }
    }

    //显示 pre 数组，和 dis 数组
    public void show(){
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++){
            for (int i = 0; i < dis.length; i++){
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            for (int i = 0; i < dis.length; i++){
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是"+dis[k][i] + " )");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;
        //从中间顶点的遍历,k 为中间顶点的下标，  {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
        for (int k = 0 ; k < dis.length ;k++){
            //从 i 顶点开始出发 ， {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
            for (int i = 0; i < dis.length; i++){
                //终点 ,  {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                for (int j = 0 ; j < dis.length ; j++){
                    //从i顶点出发 经过 k  顶点 到达 j 的距离
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]){
                        //更新距离
                        dis[i][j] = len;
                        //更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}