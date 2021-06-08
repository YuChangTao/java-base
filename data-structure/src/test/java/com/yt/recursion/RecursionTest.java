package com.yt.recursion;

import org.junit.Test;

/**
 * 1. 当程序执行到一个方法时，就会开辟一个独立的空间（栈帧）
 * 2. 每个空间（栈帧）的数据是独立的（局部变量表）
 *
 * 深度递归易产生StackOverflowError
 */
public class RecursionTest {

	@Test
	public void recursion() {
		// TODO Auto-generated method stub
		//通过打印问题，回顾递归调用机制
		test(10);
		
		int res = factorial(3);
		System.out.println("res=" + res);
	}

	//打印问题. 
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		} //else {
			System.out.println("n=" + n);
		// }
	}

	//阶乘问题
	public static int factorial(int n) {
		if (n == 1) { 
			return 1;
		} else {
			return factorial(n - 1) * n; // 1 * 2 * 3
		}
	}


}