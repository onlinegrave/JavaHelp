package com.code_help;

public class Main {
    public static void main(String args[]) {
        What hello = new What();
        System.out.println(hello);
        System.out.println(Hello.con);
//        Hello hello2 = new Hello();
//        System.out.println(hello2);
    }

    public  static class What {
        public What() {
            System.out.println("WHAT");
        }
    }

    public static class Hello {
        public static final int con;
        static {
            con = 1;
            System.out.println("STATIC");
        }

        public Hello() {
            System.out.println("CONSTRUCTOR");
        }
    }

}
