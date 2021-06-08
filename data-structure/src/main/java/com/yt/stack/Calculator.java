package com.yt.stack;

import java.util.Objects;

public class Calculator {

    public static int priority(int opera) {
        if (opera == '*' || opera == '/') {
            return 1;
        } else if (opera == '+' || opera == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public static boolean isOpera(String val) {
        return Objects.equals(val,"+")||Objects.equals(val,"-")||Objects.equals(val,"*")||Objects.equals(val,"+/");
    }

    public static int cal(int num1, int num2, int opera) {
        int res = 0;
        switch (opera) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
