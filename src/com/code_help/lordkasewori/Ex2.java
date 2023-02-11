package com.code_help.lordkasewori;


import java.util.ArrayList;
import java.util.Scanner;

public class Ex2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        String inputTxt;
        inputTxt = "";

        while (!inputTxt.equals("end")) {

            System.out.println("Enter a string: ");
            inputTxt = input.nextLine();


            if (inputTxt.equals("")) {
                System.out.println("Null inputs will get you nowhere...");
            }
            if (inputTxt.equals("end")) {
                System.out.println("Here is your string list: ");
            }
            if (!inputTxt.equals("end") && !inputTxt.equals("")) {
                list.add(inputTxt);
            }

        }

        // Printing the comma separated string
        System.out.println(SomeClass.splitByComma(list));
        input.close();
    }
}
