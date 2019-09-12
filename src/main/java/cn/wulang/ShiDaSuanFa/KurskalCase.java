package cn.wulang.ShiDaSuanFa;

import java.util.Arrays;

/**
 * @author wulang
 * @create 2019/8/27/22:04
 */
public class KurskalCase {
    //边数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //连接矩阵
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        char[] vertexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};
        KurskalCase kruskalCase = new KurskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));
        System.out.println("排序后：");
        kruskalCase.showEdges(edges);
        System.out.println(Arrays.toString(edges));
        kruskalCase.kurskal();
    }

    public KurskalCase(char[] vertexs,int[][] matrix){
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        //初始化顶点，用的是 复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0 ; i < vertexs.length ; i++){
            this.vertexs[i] = vertexs[i];
        }
        this.vertexs = vertexs;
        //初始化边， 复制拷贝
//        this.matrix = new int[vlen][vlen];
//        for (int i = 0;i < vlen; i++){
//            for (int j = 0 ; j < vlen ; j++){
//                this.matrix[i][j] = matrix[i][j];
//            }
//        }
        this.matrix = matrix;
        for (int i = 0 ; i < vlen;i++){
            for (int j = i+1 ; j < vlen ; j++){
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    public void print(){
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length ; i++){
            for (int j = 0 ; j < vertexs.length ; j++){
                System.out.printf("%15d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理
    private void showEdges(EData[] edges){
        for (int i = 0 ; i < edges.length - 1 ; i++){
            for (int j = 0; j < edges.length - 1 - i ; j++){
                if (edges[j].weight > edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }
    //顶点的下标
    private int getPosition(char ch){
        for (int i = 0 ; i < vertexs.length ; i++){
            if (vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边，放到EData[] 数组中，后面需要遍历该数组
    //是通过 matrix 邻接矩阵 来获取
    private EData[] getEdges(){
        int index = 0;
        //这里是初始化多少条边
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length ; i++){
            for (int j = i + 1 ; j < vertexs.length ; j++){
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //获取下标为 i 的顶点的终点
    //用于后面判断两个顶点的终点是否相同
    /**
     *
     * @param ends  ： 数组记录了各个顶点对应的终点是哪个，ends 数组在遍历过程中，逐步形成
     * @param i
     * @return  返回的是 下标为 i 的顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void kurskal(){
        //表示最后结果数组的索引
        int index = 0;
        //用于保存“已有最小生成树”中每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        //获取图中所有的边的集合,一共有12条边
        EData[] edges = getEdges();
        System.out.println("获取图的边的集合：" + Arrays.toString(edges) + ",共" + edges.length + "条边");

        //按照边的权值大小进行排序
        showEdges(edges);
        //遍历 edges 数组，
        //将边加入到最小生成树中，判断是否加入的边是否构成回路
        for (int i = 0 ; i < edgeNum; i++){
            //获取到第 i 条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            //获取到第 i 条边的第二个顶点(终点)
            int p2 = getPosition(edges[i].end);
            //获取 p1 这个顶点在已有最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取 p2 这个顶点在已有最小生成树中的终点
            int n = getEnd(ends,p2);

            //判断加入是否构成回路
            if (m != n){
                //不构成回路
                //设置m 在 “已有最小生成树” 中的终点
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为 = " + Arrays.toString(rets));
    }
}

class EData{
    char start;
    char end;
    //权值
    int weight;

    public EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}