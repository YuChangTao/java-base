package com.yt.stack;

import org.junit.Test;

import java.util.Scanner;

public class StackTest {

    /**
     * 出入栈测试
     */
    @Test
    public void testArrayStack() {
        //创建容量为3的栈
//        ArrayStack<Integer> stack = new ArrayStack<Integer>(3);
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("pu(push): 入栈");
            System.out.println("po(poll): 出栈");
            System.out.println("e(exit): 退出程序");

            key = scanner.next();//接收用户输入
            switch (key) {
                case "pu": //入栈
                    try {
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                    } catch (StackOverflowError e) {
                        e.printStackTrace();
                    }
                    break;
                case "po": //出栈
                    try {
                        Object res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "e": //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}
