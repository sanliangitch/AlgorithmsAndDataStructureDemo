package cn.wulang.Sort;

import java.util.Arrays;

/**
 * 归并排序：空间换时间
 *
 * 会定义一个临时数组
 *
 * @author wulang
 * @create 2019/8/1/19:54
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5,6,3,0,1,5,7,9,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));
    }

    //分开
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2 ;
            //向左递归分解
            mergeSort(arr,left,mid,temp);
            //向右递归分解
            mergeSort(arr,mid + 1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并
    /**
     *
     * @param arr  要排序的数组
     * @param left  第一个数组的左指针
     * @param rigth 最后一个指针
     * @param mid   当中一个指针，mid + 1 时为第二个数组的头指针
     * @param temp   临时数组
     */
    public static void merge(int[] arr,int left,int mid,int rigth,int[] temp){
        int i = left;
        int j = mid + 1 ;
        int t = 0;

        //第一步
        while (i <= mid && j <= rigth){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                ++t;
                ++i;
            }else {
                temp[t] = arr[j];
                ++t;
                ++j;
            }
        }
        //第二步 如果数组还有剩余的全部填充到temp数组里面去
        while (i <= mid){
            temp[t] = arr[i];
            ++t;
            ++i;
        }
        while (j <= rigth){
            temp[t] = arr[j];
            ++t;
            ++j;
        }
        //第三步  将数组全部合并
        t = 0;
        int tempLeft = left;
        while (tempLeft <= rigth){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
