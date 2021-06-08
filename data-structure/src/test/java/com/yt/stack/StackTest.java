package com.yt.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
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

    /**
     * 使用栈完成计算器
     */
    @Test
    public void testCalculator() {
        String expression = "12+3-3*2";

        //创建两个栈，数栈、操作符栈
        ArrayStack<Integer> numStack = new ArrayStack<>(10);
        ArrayStack<Integer> operaStack = new ArrayStack<>(10);

        //定义需要操作的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        String ch = "";
        String regex = "(?<=op)|(?=op)".replace("op", "[-+*/()]");
        String[] calElement = expression.split(regex);
        while (true) {
            String element = calElement[index];
            if (Calculator.isOpera(element)) {  //如果是运算符

            }
        }
    }

    @Test
    public void testItr () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
}
