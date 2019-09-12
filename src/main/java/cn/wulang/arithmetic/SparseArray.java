package cn.wulang.arithmetic;

/**
 * 稀疏数组
 *
 * @author wulang
 * @create 2019/7/11/21:54
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 1;
        //输出原始二维数组
        System.out.println("原始二维数组");
        for (int[] row : chessArr1){
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非 0 数据的个数
        int sum = 0;
        for (int i = 0; i < 11 ; i++){
            for (int j = 0; j < 11 ; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //初始化稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = 3;
        //便利数组把非零值加入到稀疏数组中
        int count = 0;
        for (int i = 0; i < 11 ; i++){
            for (int j = 0; j < 11 ; j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("输出稀疏数组");
        for (int[] row : sparseArr){
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //通过稀疏数组输出原来的数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1 ; i < sparseArr.length ; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出原始二维数组
        System.out.println("稀疏数组换为二维数组");
        for (int[] row : chessArr2){
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
