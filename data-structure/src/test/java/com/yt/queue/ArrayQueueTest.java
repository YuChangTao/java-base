package com.yt.queue;

import org.junit.Test;

import java.util.Scanner;

public class ArrayQueueTest {

    @Test
    public void testArrayQueue() {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("a(push): 添加数据到队列");
            System.out.println("g(poll): 从队列取出数据");
            System.out.println("h(getFirst): 查看队列头的数据");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    try {
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        queue.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.poll();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.getFirst();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }

    @Test
    public void testCircleArrayQueue() {
        //创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4);//说明设置4, 其队列的有效数据最大是3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("a(push): 添加数据到队列");
            System.out.println("g(poll): 从队列取出数据");
            System.out.println("h(getFirst): 查看队列头的数据");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    try {
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        queue.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.poll();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.getFirst();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}
