package com.code_help;

import java.util.*;

public class ArraysProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputCount = 0;

        int gradeCount = -1;
        do {
            int[] grades;
            try {
                System.out.println("How many grades are your putting in?");
                gradeCount = Integer.parseInt(sc.nextLine());
                grades = new int[gradeCount];
                while (inputCount < gradeCount) {
                    try {
                        System.out.println("What is the grade?");
                        grades[inputCount] = Integer.parseInt(sc.nextLine());
                        inputCount = inputCount + 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter your grade again");
                    }
                }
                Grade.sortGrades(grades);
                Grade.getAverage(grades);
                Grade.getResult(grades);
            } catch (NumberFormatException e) {
                // Sly fellow
            }

        } while (gradeCount < 0);
    }
}

class Grade {

    public static void getAverage(int[] grades) {
        System.out.println("Your average for the class was " + average(grades));
    }

    public static void getResult(int[] grades) {
        // Assuming that marks >= 40 == "PASS"
        System.out.printf(Locale.ENGLISH, "You %s the class!%n", average(grades) >= 40.0 ? "passed" : "failed");

    }

    private static double average(int[] grades) {
        return ((double) Arrays.stream(grades).sum() / grades.length);
    }


    public static void sortGrades(int[] grades) {
        HashMap<String, Integer> group = new HashMap<>();
        group.put("A", 0);
        group.put("B", 0);
        group.put("C", 0);
        group.put("D", 0);
        group.put("E", 0);
        group.put("F", 0);

        for (Integer grade : grades) {
            String k = "";
            if(grade>=80) {
                k = "A";
            }else if(grade >= 70) {
                k = "B";
            }else if(grade >= 60) {
                k = "C";
            }else if(grade >= 50) {
                k = "D";
            }else if(grade >= 40) {
                k = "E";
            }else {
                k = "F";
            }
            group.put(k, group.get(k) + 1);
        }
        System.out.println(String.format(Locale.ENGLISH,"There are %d As, %d Bs, %d Cs, %d Ds, %d Es, %d Fs", group.get("A"), group.get("B"), group.get("C"), group.get("D"), group.get("E"), group.get("F")));

    }
}
