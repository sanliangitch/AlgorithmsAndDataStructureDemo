package cn.wulang.ShiDaSuanFa;

import java.util.Arrays;

/**
 * @author wulang
 * @create 2019/8/29/7:25
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showGraph();
    }
}
class VisitedVertex {
    //记录顶点是否被访问过
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出出发顶点到其他顶点的距离，求得最短距离会放到dis
    public int[] dis;



    public VisitedVertex(int lenght,int index){
        this.already_arr = new int[lenght];
        this.pre_visited = new int[lenght];
        this.dis = new int[lenght];
        //初始化 dis
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断 index 顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        if (this.already_arr[index] == 1){
            return true;
        }
        return false;
    }

    /**
     * 更新出发顶点到 index 的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        this.dis[index] = len;
    }

    /**
     * 更新 pre 这个顶点的前驱为 index 的节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        this.pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到 index 顶点的距离
     * @param index
     */
    public int getDis(int index){
        return this.dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return
     */
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++){
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新顶点被访问过
        return index;
    }

    public void show() {
        System.out.println("==========================");
        for (int i : already_arr){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;
    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //显示图
    public void showGraph(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     *
     * @param index  表示出发顶点对应的下标
     */
    public void dsj(int index){
        this.vv = new VisitedVertex(vertex.length, index);
        System.out.println("--------");
        //更新index下标顶点到周围顶点的距离和前驱顶点
        update(index);
        System.out.println("--------");
        for (int j = 1 ; j < vertex.length ; j++){
            //选择并返回新的访问节点
            index = vv.updateArr();
            update(index);
        }
    }

    //更新 index 下标顶点到周围顶点的距离和周围顶点的前驱节点
    private void update(int index){
        int len = 0;
        for (int j = 0; j < matrix[index].length ; j++){
            //含义是   ：出发顶点到 index 顶点的距离 + index 顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            //如果 j 顶点没有被访问过，并且len 小于 出发顶点到 j 顶点的距离
            if (!vv.in(index) && len < vv.getDis(j)){
                //更新 j 顶点的前驱节点为 index 顶点
                vv.updatePre(j,index);
                //更新出发顶点 到 j 顶点的距离
                vv.updateDis(j,len);
            }
        }
    }

    public void showDijkstra(){
        vv.show();
    }
}