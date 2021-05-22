package com.yt.serial;

import java.io.*;

/**
 * @author yut
 * @version 1.0
 * 2021/2/25 15:06
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializeUser();
        deserializeUser();
    }

    private static void deserializeUser() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("G:\\tmp\\user"));
        User user = (User) inputStream.readObject();
        System.out.println("反序列化:age=" + user.getAge()+",sex="+user.sex);

       /* ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("G:\\tmp\\user"));
        User1 user1 = (User1) inputStream.readObject();
        System.out.println("反序列化:name=" + user1.getName());*/
    }

    private static void serializeUser() throws IOException {

        User user = new User();
        user.setAge(20);
        user.setName("madi");
        user.sex = "男";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("G:\\tmp\\user"));
        outputStream.writeObject(user);
        outputStream.close();
        user.sex = "女";
        System.out.println("序列化：age=" + user.getAge());

       /* User1 user1 = new User1();
        user1.setName("yutao");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("G:\\tmp\\user"));
        outputStream.writeObject(user1);
        outputStream.close();

        System.out.println("序列化：name=" + user1.getName());*/

    }


}
