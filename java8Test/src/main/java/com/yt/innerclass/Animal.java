package com.yt.innerclass;

import java.lang.invoke.CallSite;

public class Animal {


    private String name = "小黄";

    public void eat() {

    }

    /**
     * 成员内部类，不可使用static修饰方法和变量（final修饰除外）
     */
    class Bird {

        private String name = "小白";

        private final static int age = 1;

        public void display(String name) {
            System.out.println(name);//方法的形参
            System.out.println(this.name);//内部类的属性
            System.out.println(Animal.this.name);//外部类的属性
            Animal.this.eat(); //外部类方法
        }

    }

    /**
     * 静态成员内部类
     */
    static class Dog {
        private String name = "小黑";

        public void bark() {
            System.out.println(this.name);
            System.out.println("汪汪");
        }

        public static void run() {
            System.out.println("跑");
        }
    }


    /**
     * 局部内部类
     * @return
     */
    public Pig getPig1() {
        class Pig1 extends Pig{

            @Override
            public void eat() {
                System.out.println("pig1吃糠");
            }
        };
        return new Pig1();
    }

    /**
     * 局部匿名内部类
     * @return
     */
    public Pig getPig2() {
        return new Pig() {
            @Override
            public void eat() {
                System.out.println("pig2吃糠");
            }
        };
    }


    /**
     * 抽象成员内部类，也可加static修饰
     */
    abstract class Pig {
        public abstract void eat();
    }

    /**
     * 成员内部类，也可加static修饰，不可以被继承
     */
    final class Chicken {

    }

}
