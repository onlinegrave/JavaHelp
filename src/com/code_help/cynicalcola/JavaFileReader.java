package com.code_help.cynicalcola;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * How can I count the total number of words in a txt file using java?
 *
 */
public class JavaFileReader {
    public static void main(String[] args) throws FileNotFoundException {
        int totalWord = 0;
        int totalChar = 0;
        System.out.println("Enter filename: ");
        try(Scanner scanner = new Scanner(new File("asyoulike.txt"))) {
            while(scanner.hasNext()) {
                scanner.next();
                totalWord++;
            }
            System.out.println(totalWord);
            System.out.println("Total Characters: "+totalChar);
        }
    }

}
