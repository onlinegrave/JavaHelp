package com.code_help.cynicalcola;

import java.util.*;

/**
 * IN this programming project, your task is evaluating the performance of Array, ArrayList and Vector collections.
 * To evaluate, please complete the following steps:
 *
 * 1. Create an Array, an ArrayList, and a Vector.
 * 2. Generate 50,000,000 integer numbers between 0 to Integer.MAX_VALUE.
 * 3. Calculate the times that take to add 50,000,000 numbers into the Array, ArrayList, and Vector that you created.
 * 4. Report the times that take to add these number to these collections.
 * 5. Calculate the times that take to sort these numbers using Arrays.sort() for the array and Collections.sort() for the ArrayList and Vector collections.
 * 6. Report the times that take to sort these numbers.
 */
public class ProgrammingProject1 {
    public static void main(String[] args) {
        byte b = 87;
        System.out.println("B".compareTo("a"));
        System.out.println('A');
//        System.out.println(Arrays.toString(i));
//        int copy = Arrays.copyOf(i,i.length);
//        Arrays.sort(i);
//        System.out.println(Arrays.toString(i));

//        t1();
//        System.out.println("\n\n");
//        t2();
    }

    public static void t1() {
        int s1[] = new int[50_000_000];
        ArrayList<Integer> s2 = new ArrayList<>();
        Vector<Integer> s3 = new Vector<>();
        int LENGTH = 50_000_000;


        System.out.println("\nTask 2");
        System.out.println("Array");
        long startTime = System.nanoTime();
        for(int i=0;i<LENGTH;i++) {
            s1[i] = i;
        }
        long endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("ArrayList");
        startTime = System.nanoTime();
        for(int i=0;i<LENGTH;i++) {
            s2.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("Vector");
        startTime = System.nanoTime();
        for(int i=0;i<LENGTH;i++) {
            s3.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);


        System.out.println("\n\nTask 3");
        System.out.println("Array");
        startTime = System.nanoTime();
        Arrays.sort(s1);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("ArrayList");
        startTime = System.nanoTime();
        Collections.sort(s2);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("Vector");
        startTime = System.nanoTime();
        Collections.sort(s3);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);
    }

    public static void t2() {
        int s1[] = new int[50_000_000];
        ArrayList<Integer> s2 = new ArrayList<>();
        Vector<Integer> s3 = new Vector<>();
        int LENGTH = 50_000_000;
        int start = Integer.MAX_VALUE;
        int stop = start- LENGTH;

        System.out.println("\nTask 2");
        System.out.println("Array");
        long startTime = System.nanoTime();

        for(int i=start, j=0;i > stop;i--, j++) {
            s1[j] = i;
        }
        long endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("ArrayList");
        startTime = System.nanoTime();
        for(int i=start, j=0;i > stop;i--, j++) {
            s2.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("Vector");
        startTime = System.nanoTime();
        for(int i=start, j=0;i > stop;i--, j++) {
            s3.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);


        System.out.println("\n\nTask 3");
        System.out.println("Array");
        startTime = System.nanoTime();
        Arrays.sort(s1);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("ArrayList");
        startTime = System.nanoTime();
        Collections.sort(s2);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);

        System.out.println("Vector");
        startTime = System.nanoTime();
        Collections.sort(s3);
        endTime = System.nanoTime();
        System.out.println("Time Taken: "+(endTime-startTime)/1000);
    }
}
