package cn.wulang.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author wulang
 * @create 2019/8/20/7:39
 */
public class Graph {
    private ArrayList<String> vertexKist;
    private int[][] edges;
    //有多少条边
    private int numOfEdges;
    //记录某个节点是否被共享过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String value[] = { "A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String v : value){
            graph.insertVertex(v);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.showGraph();

        System.out.println("深度遍历：");
//        graph.DFS();
        System.out.println();
        System.out.println("广度遍历：");
        graph.bfs();
    }

    public Graph(int n){
        edges = new int[n][n];
        vertexKist = new ArrayList<String>(n);
        isVisited = new boolean[n];
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i){
        //表示队列的头结点对应下标
        int u;
        //领接队列w
        int w;
        LinkedList queue = new LinkedList();
        System.out.print(getVauleByIndex(i) + "->");
        isVisited[i] = true;
        queue.add(i);
        while (!queue.isEmpty()){
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            w = getFirstNegihbor(u);
            while (w != -1){
                //找到
                //是否访问过
                if (!isVisited[w]){
                    System.out.print(getVauleByIndex(w) + "-->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //体现广度优先
                w = getNextNegihbor(u,w);

            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    public void bfs(){
        for (int i = 0 ; i < getNumOfVertex(); i++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
    //得到第一个连接节点的下标
    public int getFirstNegihbor(int index){
        for (int j = 0 ; j < vertexKist.size() ; j++){
            if (edges[index][j] > 0 ){
                return j;
            }
        }
        return -1;
    }

    //根据前一个连接节点获得下一个连接节点
    public int getNextNegihbor(int v1,int v2){
        for (int j = v2 + 1 ; j < vertexKist.size() ;j++){
            if (edges[v1][j] > 0 ){
                return j;
            }
        }
        return -1;
    }

    //深度优先 DFS
    private void DFS(boolean[] isVisited,int i){
        System.out.print(getVauleByIndex(i) + "->");
        isVisited[i] = true;
        //查找节点i的第一个节点w
        int w = getFirstNegihbor(i);
        while (w != -1){
            if (!isVisited[w]){
                DFS(isVisited,w);
            }
            //如果存在，但已经被访问过
            w = getNextNegihbor(i,w);
        }
    }
    //对DFS进行重载,遍历所有的节点，并进行DFS
    public void DFS(){
        for (int i = 0 ; i < getNumOfVertex() ; i++){
            if (!isVisited[i]){
                DFS(isVisited,i);
            }
        }
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexKist.size();
    }
    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i（下标） 对应的数据
    public String getVauleByIndex(int i){
        return vertexKist.get(i);
    }
    //返回权值
    public int getWeignt(int v1,int v2){
        return edges[v1][v2];
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexKist.add(vertex);
    }
    //插入边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
