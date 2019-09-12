package cn.wulang.Sort;

import java.util.Arrays;

/**
 * @author wulang
 * @create 2019/7/29/7:37
 */
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = new int[8];
        for (int i = 0 ; i < 8; i++){
            arr[i] = (int)( Math.random() * 8);
        }
        insertSort(arr);
    }
    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1 ; i < arr.length ; i++){
            insertVal = arr[i];
            insertIndex = i - 1 ;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex +1 != i){
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println("第"+ i + "轮:"+ Arrays.toString(arr));
        }
    }
}
