package com.yt.sparsearray;

/**
 * 二维数组
 * 稀疏数组
 *
 * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 *
 * 稀疏数组的处理方法：
 * 1）记录数组一共有几行几列，有多少个不同的值
 * 2）把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 *
 */
public class SparseArray {

    /**
     * 二维数组转稀疏数组
     *
     * @param twoDimensionalArray
     * @return
     */
    public static int[][] twoDimensionalToSparseArray(int[][] twoDimensionalArray) {
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (twoDimensionalArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值，二维数组行、列、非0元素个数
        sparseArr[0][0] = twoDimensionalArray.length;
        sparseArr[0][1] = twoDimensionalArray[0].length;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (twoDimensionalArray[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = twoDimensionalArray[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArr
     * @return
     */
    public static int[][] sparseToTwoDimensionalArray(int[][] sparseArr) {
        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int rawArray[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1; i < sparseArr.length; i++) {
            rawArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组~~");

        for (int[] row : rawArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return rawArray;
    }

}
