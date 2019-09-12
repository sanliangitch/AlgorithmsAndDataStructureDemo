package cn.wulang.ShiDaSuanFa;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 骑士周游问题
 *
 * @author wulang
 * @create 2019/8/31/22:19
 */
public class HorseChessboard {
    //列
    private static int X;
    //行
    private static int Y;
    //标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性标记是否棋盘的所有位置都被访问过
    private static boolean finished;
    public static void main(String[] args) {
        System.out.println("开始冲！");
        X = 8;
        Y = 8;
        //行
        int row = 1;
        //列
        int column = 1;
        int[][] chessborad = new int[X][Y];
        visited = new boolean[X * Y];
        traversalChessborad(chessborad,row - 1,column - 1,1);
        for (int[] i : chessborad){
            for (int j : i){
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param chessborad 棋盘
     * @param row  行 从0开始
     * @param column  列 从 0开始
     * @param step  是第几步，初始位置从 1 开始
     */
    public static void traversalChessborad(int[][] chessborad,int row,int column,int step){
            chessborad[row][column] = step;
            //row = 4,X = 8,column = 4,  ---->  4 * 8 + 4 = 36
            visited[row * X  + column] = true;
            //获取当前位置可以走的下一个位置的集合
            ArrayList<Point> ps = next(new Point(column, row));
            //对ps进行排序
            sore(ps);
            while (!ps.isEmpty()){
                Point p = ps.remove(0);
                //判断是否已经被访问过
                if (!visited[p.y * X + p.x]){
                    traversalChessborad(chessborad,p.y,p.x,step+1);
                }
            }
            //判断是否完成
            if (step < X * Y && !finished){
                chessborad[row][column] = 0;
                visited[row * X  + column] = false;
            }else {
                finished = true;
            }
    }
    /**
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //走 5
        if ((p1.x = curPoint.x-2) >= 0 && (p1.y = curPoint.y-1) >= 0){
            ps.add(new Point(p1));
        }
        //6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

    /**
     * 优化：贪心算法
     * @param ps
     */
    public static void sore(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2){
                    return -1;
                }else if (count1 == count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
