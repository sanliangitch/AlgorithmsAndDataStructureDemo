package cn.wulang.Recursion;

/**
 * @author wulang
 * @create 2019/7/23/7:28
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map =  new int[8][7];
        for (int i = 0 ;i < 7 ;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0 ;j < 8 ;j++){
            map[j][0] = 1;
            map[j][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图情况
        for (int i = 0; i < 8 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("------------------------------------------");
        for (int i = 0; i < 8 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public static boolean setWay(int[][] map,int i ,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            //这个点没有走过
            if (map[i][j] == 0){
                //先假设可以走
                map[i][j] = 2;
                //向下
                if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    //向右
                    return true;
                }else if (setWay(map,i-1,j)){
                    //向上
                    return true;
                }else if (setWay(map,i,j-1)){
                    //向左
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else { //如果map[i][j] != 0，则有可能为1，2，3
                return false;
            }
        }
    }
}
