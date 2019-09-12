package cn.wulang.ShiDaSuanFa;

import java.util.Arrays;

/**
 * prim: 创建最小生成树
 *
 * @author wulang
 * @create 2019/8/27/7:35
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);
    }
}

//创建最小生成树----> 村庄的图
class MinTree{
    /**
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图各顶点对应的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        int i,j;
        for (i = 0 ; i < verxs ; i++){
            graph.data[i] = data[i];
            for (j = 0 ; j < verxs ; j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //编写 Prim  算法，得到最小生成树
    /**
     *
     * @param graph  图
     * @param v 从哪个顶点开始
     */
    public void prim(MGraph graph,int v){
        //标记顶点是否被访问过
        int visited[] = new int[graph.verxs];
        for (int i = 0 ; i < graph.verxs ; i++){
            visited[i] = 0;
        }
        //先把当前节点标记为已访问
        visited[v] = 1;
        //h1 和 h2 表示两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        int minWegight = 10000;
        //prim 算法后，会有  graph.verxs - 1 条边
        for (int k = 1; k < graph.verxs ; k++){
            // i 表示为被访问过的节点
            for (int i = 0 ; i < graph.verxs ; i++){
                // j 表示为没有访问过得节点
                for (int j = 0 ; j < graph.verxs ; j++){
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWegight){
                        //替换minWegight（寻找已访问过的节点和未访问过节点间权值最小的边）
                        minWegight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //退出上面for循环时，已经找到最小的了
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">,权值为：" + minWegight);
            //将当前找到的节点标记为已访问过
            //(因为上面的节点 i 默认是已访问过，j 默认是未访问过)
            visited[h2] = 1;
            //重置 minWegight ，为了下次寻找
            minWegight = 10000;
        }
    }
}

//图
class MGraph{
    //节点数
    int verxs;
    //节点数据
    char[] data;
    //存放边，就是 邻接矩阵
    int[][] weight;

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
