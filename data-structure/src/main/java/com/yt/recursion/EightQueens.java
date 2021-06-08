package com.yt.recursion;

/**
 * 八皇后问题
 * 在8x8的国际象棋上摆放八个皇后，使其不能相互攻击，即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法（92）
 *
 * 理论上应该创建一个二维数组来表示棋盘，但实际上可以通过算法，用一个一维数组即可解决问题，
 * arr[8]={0,4,7,5,2,6,1,3} 下标表示第几行，即第几个皇后，arr[i]表示第几列，即i+1个皇后放在第i+1行的第arr[i]列
 */
public class EightQueens {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个数组，保存皇后放置位置的结果
    int[] array = new int[8];

    static int count = 0;
    static int checkCount = 0;

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.playChess(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", checkCount); // 1.5w
    }

    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * 放置皇后
     *
     * @param n 表示第几个皇后
     * @return
     */
    public void playChess(int n) {
        if(n == max) {  //n = 8 , 其实8个皇后就既然放好
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(check(n)) { // 不冲突
                //接着放n+1个皇后,即开始递归
                playChess(n+1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行后移的一个位置
        }
    }

    /**
     * 当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第几个皇后
     * @return
     */
    public boolean check(int n) {
        checkCount ++ ;
        for (int i = 0; i < n; i++) {
            //同一列和同一斜线（sin45°=1）判断
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]) ) {
                return false;
            }
        }
        return true;
    }
}
