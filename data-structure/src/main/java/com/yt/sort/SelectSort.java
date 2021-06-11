package com.yt.sort;

/**
 * 选择排序的基本思想：第一次从arr[0]-arr[[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]-arr[n-1]中选取最小值，与arr[1]交换，......
 * <p>
 * 相对于冒泡算法，数据交换变少了会快很多
 * 时间复杂度O(n²)
 */
public class SelectSort {

    public static int[] sort(int[] arr) {
        //选择的次数，arr.length - 1
        for (int i = 0; i < arr.length - 1; i++) {
            //最小值的下标
            int minIndex = i;
            //选择的范围
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            //交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        return arr;
    }
}
