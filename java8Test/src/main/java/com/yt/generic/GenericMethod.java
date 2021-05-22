package com.yt.generic;

/**
 * 泛型方法
 */
public class GenericMethod {

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
    }
}
