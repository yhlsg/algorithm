package com.yhl.binarysearchnorecu;

/**
 * 二分查找(非递归)
 * @author yhl
 * @create 2021-08-06 20:55
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        int res = binarySearch(arr, 20);//1
        System.out.println(res);

    }

    /**
     * 二分查找(非递归)
     * @param arr 待查找的数组
     * @param value 查找的值
     * @return 找到返回在数组中的下标， 没有找到返回-1
     */
    public static int binarySearch(int[] arr, int value){

        int left = 0;//左边下标
        int right = arr.length - 1;//右边下标
        //循环遍历
        while (left <= right){
            //中间值
            int mid = (left + right) / 2;
            //找到，返回下标
            if (value == arr[mid]){
                return mid;
            }
            //向左二分查找
            if (value < arr[mid]){
                right = mid - 1;
            }
            //向右二分查找
            else {
                left = mid + 1;
            }
        }

        //没有找到，返回-1
        return -1;
    }
}
