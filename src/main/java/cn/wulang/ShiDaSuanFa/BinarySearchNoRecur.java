package cn.wulang.ShiDaSuanFa;

/**
 * 二分查找（非递归）
 *
 * @author wulang
 * @create 2019/8/22/7:43
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,2,3,6,7,8,9,10,11,13};
        int target = 8;
        System.out.println(binarySearch(arr,target));
    }

    public static int binarySearch(int[] arr,int target){
            int left = 0;
            int rihght = arr.length - 1;
            while (left <= rihght){
                int mid = (left + rihght) / 2 ;
                if (arr[mid] == target){
                    return mid;
                }else if (arr[mid] > target){
                    rihght = mid - 1 ;
                }else if (arr[mid] < target){
                    left = mid + 1 ;
                }
            }
            return -1;
    }
}
