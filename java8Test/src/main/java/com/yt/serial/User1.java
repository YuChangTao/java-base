package com.yt.serial;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author yut
 * @version 1.0
 * 2021/2/25 15:24
 */
public class User1 implements Externalizable {

    private transient String name;
    private int age;

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(age);
        out.writeBytes(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        age = in.readInt();
        name = in.readLine();
    }
}
