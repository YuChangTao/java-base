package com.yt.sort;

/**
 * 希尔插入排序是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 * <p>
 * 希尔插入排序的基本思想：把记录按下标的一定增量分组，对每组使用简单插入排序算法排序，随着增量逐渐减少，
 * 每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * <p>
 * 时间复杂度O(n²)
 */
public class ShellSort {

    /**
     * 希尔排序
     * <p>
     * 移位法
     *
     * @param arr
     * @return
     */
    public static int[] shellSortByShift(int[] arr) {
        //gap表示分组数和步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从每组的第二个元素开始，逐个的对其所在的组进行简单插入排序
            //这里实际操作是多个分组交替执行，并非分组执行
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i - gap;  //插入的索引位置
                int insertVal = arr[i]; //待插入的元素的值
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[i] = arr[i - gap];
                    insertIndex -= gap;
                }

                if (insertIndex + gap != i) {
                    arr[insertIndex + gap] = insertVal;
                }
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     * <p>
     * 交换法（很慢，不使用，仅测试）
     */
    public static int[] shellSortBySwap(int[] arr) {
        //gap表示分组数和步长
        int temp;
        // 根据前面的逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

        return arr;
    }
}
