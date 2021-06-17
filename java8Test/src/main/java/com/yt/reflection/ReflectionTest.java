package com.yt.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1. 调用某个对象的 getClass()方法
 * Person p=new Person();
 * Class clazz=p.getClass();
 * <p>
 * 2. 调用某个类的 class 属性来获取该类对应的 Class 对象
 * Class clazz=Person.class;
 * <p>
 * 3. 使用 Class 类中的 forName()静态方法(最安全/性能最好)
 * Class clazz=Class.forName("类的全路径"); (最常用)
 */
public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //获取 Person 类的 Class 对象
        Class<Person> clazz = (Class<Person>) Class.forName("com.yt.reflection.Person");

        //获取 Person 类的所有方法信息
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.toString());
        }

        //获取 Person 类的所有成员属性信息
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.toString());
        }
        //获取 Person 类的所有构造方法信息
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println(c.toString());
        }

        //使用.newInstane 方法创建对象，必须有默认空参构造器
        Person p = clazz.newInstance();

        //获取构造方法并创建对象
        Constructor c = clazz.getDeclaredConstructor(String.class, String.class, int.class);
        //创建对象并设置属性
        Person p1 = (Person) c.newInstance("李四", "男", 20);

    }
}
