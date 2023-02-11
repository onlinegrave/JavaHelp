package com.code_help.aiva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T1 {
    static List<Student> students = new ArrayList<>();
    static String FILE_NAME = "t1.txt";

    public static void main(String[] args) {
        addStudent();
    }
    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student grade: ");
        int grade = scanner.nextInt();
        students.add(new Student(name, grade));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "\n" + grade + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Student added successfully.");
    }
    static class Student {
        private String name;
        private int grade;
        Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }
    }
}
