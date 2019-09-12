package cn.wulang.Sort;

/**
 * @author wulang
 * @create 2019/7/30/20:04
 */
public class ShellSort {
    public static void main(String[] args) {

    }
    public static void shellSort(int[] arr){
        for (int gap = arr.length/2 ; gap > 0 ; gap /= 2){
            for (int i = gap; i < arr.length ; i++){
                for (int j = i - gap ; j >=0; j -= gap){
                    if (arr[j] > arr[j + gap]){
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
}
