package cn.wulang.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wulang
 * @create 2019/7/27/13:17
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];

        for(int i = 0; i < 80000; ++i) {
            arr[i] = (int)(Math.random() * 8000000.0D);
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        selectSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    public static void selectSort(int[] arr){
        for (int i = 0 ; i < arr.length - 1 ; i++){
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1 ;j<arr.length  - 1 ; j++){
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
