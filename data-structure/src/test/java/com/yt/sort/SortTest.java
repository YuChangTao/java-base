package com.yt.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    public int[] arr;
    int cap = 80000;

    @Before
    public void initArr() {

        arr = new int[cap];
        for (int i = 0; i < cap; i++) {
            arr[i] = (int) (Math.random() * cap * 1000);
        }
    }

    //    @After
    public void print() {
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序
     */
    @Test
    public void testBubbleSort() {

        long startTs = System.currentTimeMillis();

        BubbleSort.sort(arr);

        long endTs = System.currentTimeMillis();

        System.out.println("耗时：" + (endTs - startTs));

    }

    /**
     * 选择排序
     */
    @Test
    public void testSelectSort() {
        long startTs = System.currentTimeMillis();

        SelectSort.sort(arr);

        long endTs = System.currentTimeMillis();

        System.out.println("耗时：" + (endTs - startTs));
    }

    /**
     * 插入排序
     * - 简单插入排序
     * - 希尔排序
     * <p>
     * 测试速度：shellSortByShift>simpleSort>shellSortBySwap
     */
    @Test
    public void testInsertSort() {
        long startTs = System.currentTimeMillis();
        //简单插入排序
//        InsertSort.simpleSort(arr);

        //希尔排序，移位法（推荐）
        ShellSort.shellSortByShift(arr);

        //希尔排序，交换法
//        ShellSort.shellSortBySwap(arr);

        long endTs = System.currentTimeMillis();

        System.out.println("耗时：" + (endTs - startTs));
    }


}
