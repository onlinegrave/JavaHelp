package com.code_help.sky;

import java.util.Arrays;

public class Fill {
    public static void main(String[] args) {
        int[] a = new int[18];
        for (int i = 0; i < a.length; i++) {
            a[i] = i%3;
        }
        System.out.println(Arrays.toString(a));
    }
}