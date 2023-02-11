package com.code_help.chey;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!

        Scanner sc = new Scanner(System.in);
        String temp;

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            sc.nextLine();
            String[] array = new String[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextLine();
            }
            //System.out.println(Arrays.toString(array)); //Arreglo de palabras a ordenar


            //Ordenando las palabras
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    if (array[i].substring(0, 2).compareTo(array[j].substring(0, 2)) > 0) {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }

            //System.out.println(Arrays.toString(array)); //Arreglo ordenado

            Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);//case insensitive sort

            //Imprimiendo el contenido del arreglo
            for (String name : array) {
                System.out.println(name);
            }
            System.out.println();
        }
        sc.close();
    }
}