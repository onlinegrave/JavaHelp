package com.code_help.eric_romario;

import java.util.Scanner;

public class OddNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            System.out.println(oddNumber(words[i]));
        }
    }

    private static String oddNumber(String word) {
        var even = new StringBuilder();
        var odd = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i % 2 == 0) even.append(word.charAt(i));
            else odd.append(word.charAt(i));
        }
        return even.append(" ").append(odd).toString();
    }
}