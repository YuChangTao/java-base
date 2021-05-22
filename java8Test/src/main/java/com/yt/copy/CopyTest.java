package com.yt.copy;

import org.junit.Test;

import java.io.*;

public class CopyTest {

    /**
     * 赋值，引用复制，指向同一对象
     */
    @Test
    public void assignment() {
        Person p1 = new Person();
        Person p2 = p1;
    }

    /**
     * 浅拷贝
     * 实现方式一：通过拷贝构造方法实现浅拷贝
     */
    @Test
    public void shallowCopy1() {
        Person.Age a = new Person.Age(20);
        Person p1 = new Person(a, "摇头耶稣");

        //创建新对象，对象内的属性使用被赋值对象的
        Person p2 = new Person(p1);

        System.out.println("p1是" + p1);
        System.out.println("p2是" + p2);

        //修改p1的各属性值，观察p2的各属性值是否跟随变化
        p1.setName("小傻瓜");
        a.setAge(99);
        System.out.println("修改后的p1是" + p1);
        System.out.println("修改后的p2是" + p2);
    }

    /**
     * 浅拷贝
     * 实现方式二：重写clone()方法进行浅拷贝（必须实现Cloneable接口）
     */
    @Test
    public void shallowCopy2() throws CloneNotSupportedException {
        Person.Age a = new Person.Age(20);
        Person p1 = new Person(a, "摇头耶稣");

        //创建新对象，对象内的属性使用被赋值对象的
        Person p2 = (Person) p1.clone();

        System.out.println("p1是" + p1);
        System.out.println("p2是" + p2);

        //修改p1的各属性值，观察p2的各属性值是否跟随变化
        p1.setName("小傻瓜");
        a.setAge(99);
        System.out.println("修改后的p1是" + p1);
        System.out.println("修改后的p2是" + p2);
    }

    /**
     * 深拷贝
     * 实现方式一：重写clone()方法进行深拷贝（必须实现Cloneable接口）
     * 为对象图的每一层的每一个对象都实现Cloneable接口并重写clone方法，最后在最顶层的类的重写的clone方法中调用所有的clone方法即可实现深拷贝。
     * 简单的说就是：每一层的每个对象都进行浅拷贝=深拷贝。
     */
    @Test
    public void deepCopy1() {

        Student.Age a=new Student.Age(20);
        Student stu1=new Student("摇头耶稣",a,175);

        //通过调用重写后的clone方法进行浅拷贝
        Student stu2=(Student)stu1.clone();
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
        System.out.println();

        //尝试修改stu1中的各属性，观察stu2的属性有没有变化
        stu1.setName("大傻子");
        //改变age这个引用类型的成员变量的值
        a.setAge(99);
        //stu1.setaAge(new Age(99));    使用这种方式修改age属性值的话，stu2是不会跟着改变的。因为创建了一个新的Age类对象而不是改变原对象的实例值
        stu1.setLength(216);
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
    }

    /**
     * 深拷贝
     * 实现方式二：对象序列化实现深拷贝（必须实现Serializable接口）
     * 层次调用clone方法可以实现深拷贝，但是显然代码量实在太大。特别对于属性数量比较多、层次比较深的类而言，每个类都要重写clone方法太过繁琐。
     * 将对象序列化为字节序列后，默认会将该对象的整个对象图进行序列化，再通过反序列即可完美地实现深拷贝。
     */
    @Test
    public void deepCopy2() throws IOException, ClassNotFoundException {
        Student.Age a=new Student.Age(20);
        Student stu1=new Student("摇头耶稣",a,175);
        //通过序列化方法实现深拷贝
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(stu1);
        oos.flush();
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Student stu2=(Student)ois.readObject();

        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
        System.out.println();
        //尝试修改stu1中的各属性，观察stu2的属性有没有变化
        stu1.setName("大傻子");
        //改变age这个引用类型的成员变量的值
        a.setAge(99);
        stu1.setLength(216);
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
    }
}
