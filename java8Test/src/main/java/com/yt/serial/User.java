package com.yt.serial;

import java.io.Serializable;

/**
 * @author yut
 * @version 1.0
 * 2021/2/25 15:05
 */
public class User implements Serializable {

    public static String sex;

    private String name;
    private transient int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getSex() {
        return sex;
    }

    public static void setSex(String sex) {
        User.sex = sex;
    }
}
