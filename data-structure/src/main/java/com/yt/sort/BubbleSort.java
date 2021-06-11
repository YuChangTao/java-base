package com.yt.sort;

/**
 * 冒泡排序的基本思想：通过堆待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前
 * 移向后部，就像水底下的气泡一样逐渐向上冒。
 * <p>
 * 时间复杂度：O(n²)
 */
public class BubbleSort {

    public static int[] sort(int[] arr) {
        //标识变量，表示是否进行过交换
        boolean flag = false;

        //排序的次数，只需排arr.length-1次
        for (int i = 0; i < arr.length - 1; i++) {
            //需要排序的范围，已排的元素去除掉
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    flag = true;
                }
            }

            if (!flag) {
                // 在一趟排序中，一次交换都没有发生过，说明已经有序
                break;
            } else {
                // 重置flag!!!, 进行下次判断
                flag = false;
            }

        }
        return arr;
    }
}
