package com.yt.innerclass;

import org.junit.Test;

public class AnimalTest {


    /**
     * 静态成员内部类测试
     */
    @Test
    public void testStaticInnerClass() {
        //静态内部类静态方法调用
        Animal.Dog.run();

        //静态内部类非静态方法调用
        Animal.Dog dog = new Animal.Dog();
        dog.bark();

    }

    /**
     * 非静态成员内部类测试
     */
    @Test
    public void testInnerClass() {
        //非静态内部类创建
        Animal animal = new Animal();
        Animal.Bird bird = animal.new Bird();
        bird.display("小绿");
    }

    /**
     * 局部内部类测试
     */
    @Test
    public void testLocalInnerClass() {
        Animal animal = new Animal();
        Animal.Pig pig1 = animal.getPig1();
        pig1.eat();
    }

}
