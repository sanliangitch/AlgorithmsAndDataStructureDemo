package cn.wulang.ShiDaSuanFa;

/**
 * 分治算法：汉罗塔问题
 *
 * @author wulang
 * @create 2019/8/22/21:41
 */
public class DAC {
    public static void main(String[] args) {
        hanoiTower(5,'a','b','c');
    }

    public static void hanoiTower(int num,char a,char b,char c){
            //只有一个盘
        if (num == 1){
            System.out.println("第一个盘从" + a + "->" + c);
        }else {
            //如果n > 2 ，我们总是看成两个盘
            //先把最上面的移动到B，中间会用到C
            hanoiTower(num - 1, a, c, b);
            //把最下面的盘A -> C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //之后B -> C,中间用到A
            hanoiTower(num - 1 ,b,a,c);
        }
    }
}
