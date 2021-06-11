package com.yt.sort;

/**
 * 简单插入排序的基本思想：把n个待排序的元素看成是一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序列表中取出第一个元素，把它的排序码依次与有序元素的排序码进行比较，将它插入到有序表中的适当位置，使之
 * 成为新的有序表。
 * <p>
 * 时间复杂度O(n²)
 */
public class InsertSort {

    /**
     * 简单插入排序
     *
     * @param arr
     * @return
     */
    public static int[] simpleSort(int[] arr) {
        //从第二个元素开始插入排序，需要排序的次数是arr.length-1
        for (int i = 1; i < arr.length; i++) {
            //正在插入排序的数
            int insertVal = arr[i];
            int insertIndex = i - 1;

            // 给insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //判断是否需要交换
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        return arr;
    }



}
