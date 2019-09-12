package cn.wulang.Sort;

import java.util.Arrays;

/**
 * @author wulang
 * @create 2019/7/25/20:22
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = new int[8];
//
//        for (int i = 0 ; i < 8; i++){
//            arr[i] = (int)( Math.random() * 8);
//        }
//        bubbleSort(arr);
////        System.out.println(Arrays.toString(bubbleSort(arr)));
//        bubbleSort1(arr);
        int[] arr = new int[8];

        for(int i = 0; i < 8; ++i) {
            arr[i] = (int)(Math.random() * 8);
        }

//        Date data1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(data1);
//        System.out.println("排序前的时间是=" + date1Str);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
//        Date data2 = new Date();
//        String date2Str = simpleDateFormat.format(data2);
//        System.out.println("排序后的时间是=" + date2Str);
    }



    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0 ; i < arr.length - 1 ; i++){
            for (int m = 0 ; m < arr.length-1-i ; m++){
                if (arr[m] > arr[m+1]){
                    flag = true;
                    temp = arr[m];
                    arr[m] = arr[m+1];
                    arr[m+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag){
                break;
            }else {
                //重置flag！！！ 为下一次做判断
                flag = false;
            }
        }
    }


    public static void bubbleSort1(int[] arr) {
        int temp1 = 0;
        boolean flag = false;

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp1 = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp1;
                }
            }

            if (!flag) {
                break;
            }

            flag = false;
        }

    }
}
