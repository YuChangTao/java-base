package com.yt.copy;

import java.io.Serializable;

public class Student implements Cloneable, Serializable {
    //学生类的成员变量（属性）,其中一个属性为类的对象
    private String name;
    private Age aage;
    private int length;
    //构造方法,其中一个参数为另一个类的对象
    public Student(String name,Age a,int length) {
        this.name=name;
        this.aage=a;
        this.length=length;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Age getaAge() {
        return this.aage;
    }

    public void setaAge(Age age) {
        this.aage=age;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length=length;
    }
    public String toString() {
        return "姓名是： "+this.getName()+"， 年龄为： "+this.getaAge().toString()+", 长度是： "+this.getLength();
    }
    //重写Object类的clone方法
    public Object clone() {
        Object obj=null;
        //调用Object类的clone方法——浅拷贝
        try {
            obj= super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //调用Age类的clone方法进行深拷贝
        //先将obj转化为学生类实例
        Student stu=(Student)obj;
        //学生类实例的Age对象属性，调用其clone方法进行拷贝
        stu.aage=(Age)stu.getaAge().clone();
        return obj;
    }

    static class Age implements Cloneable,Serializable{
        //年龄类的成员变量（属性）
        private int age;
        //构造方法
        public Age(int age) {
            this.age=age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return this.age+"";
        }

        //重写Object的clone方法
        public Object clone() {
            Object obj=null;
            try {
                obj=super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }
}
