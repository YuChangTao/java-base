package com.yt.sparsearray;

import org.junit.Test;

import static com.yt.sparsearray.SparseArray.sparseToTwoDimensionalArray;
import static com.yt.sparsearray.SparseArray.twoDimensionalToSparseArray;

public class SparseArrayTest {

    @Test
    public void test() {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int checkerboard[][] = new int[11][11];
        checkerboard[1][2] = 1;
        checkerboard[2][3] = 2;
        checkerboard[4][5] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组~~~");
        for (int[] row : checkerboard) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组
        int[][] sparseArray = twoDimensionalToSparseArray(checkerboard);

        //将稀疏数组 --》 恢复成 原始的二维数组
        sparseToTwoDimensionalArray(sparseArray);
    }
}
