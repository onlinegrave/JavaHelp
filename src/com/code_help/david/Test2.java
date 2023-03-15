package com.code_help.david;

import java.util.Arrays;

/**
 * public static void replaceElement(int[][] array, int elem, int[] newElem)
 *
 * It takes a two-dimensional array and modifies it in-place (i.e. the method doesn't return anything). Every
 * occurrence of elem is replaced by the items contained in newElem. The modification must happen in-place, i.e.
 * the memory address of th whole array must remain the same; but the memory addresses of its rows can change of course.
 */
public class Test2 {
    public static void main(String[] args) {
        int[][] inputArray = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8}};
        int[][] inputArray2 = new int[][]{{1, 2, 3, 4, 5}, {5, 4, 3, 2}};
        int[][] inputArray3 = new int[][]{{0, 1, 2, 3, 4, 5}, {5, 4, 3, 4}};
        int[][] inputArray4 = new int[][]{{0, 1, 2, 3, 4, 5}, {5, 4, 3, 4}};
        int[] newElem = new int[]{0};
        int[] newElem2 = new int[]{-5,5};
        int[] newElem3 = new int[]{1,2,3};
        int[] newElem4 = new int[]{};
        replaceElement(inputArray, 2, newElem);
        System.out.println(Arrays.deepToString(inputArray));
        replaceElement(inputArray2, 5, newElem2);
        System.out.println(Arrays.deepToString(inputArray2));
        replaceElement(inputArray3, 4, newElem3);
        System.out.println(Arrays.deepToString(inputArray3));
        replaceElement(inputArray4, 4, newElem4);
        System.out.println(Arrays.deepToString(inputArray4));
    }

//    public static void replaceElement(int[][] array, int elem, int[] newElem) {
//        if (newElem.length == 1) {
//            for (int i = 0; i < array.length; i++) {
//                for (int j = 0; j < array[i].length; j++) {
//                    if (array[i][j] == elem) {
//                        array[i][j] = newElem[0];
//                    }
//                }
//            }
//            return;
//        }
//        // Now we know that we have to increase the size of the inner array.
//        int a1[] = new int[array[0].length];
//        int a2[] = new int[array[1].length];
//        int i1 = 0, i2 = 0;
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                if (array[i][j] == elem) {
//                    if (i == 0) {
//                        a1[i1] = j;
//                        i1++;
//                    } else {
//                        a2[i2] = j;
//                        i2++;
//                    }
//                }
//            }
//        }
//        int[] na1 = new int[array[0].length + (i1 * newElem.length)-i1];
//        int[] na2 = new int[array[1].length + (i2 * newElem.length)-i2];
//        int k = 0;
//        int c = 0;
//        for (int i = 0; i < array[0].length; i++) {
//            if (i == a1[k]) {
//                for (int value : newElem) {
//                    na1[c] = value;
//                    c++;
//                }
//                k++;
//            } else {
//                na1[c] = array[0][i];
//                c++;
//            }
//        }
//
//        k = 0;
//        c = 0;
//        for (int i = 0; i < array[1].length; i++) {
//            if (i == a2[k]) {
//                for (int value : newElem) {
//                    na2[c] = value;
//                    c++;
//                }
//                k++;
//            } else {
//                na2[c] = array[1][i];
//                c++;
//            }
//        }
//        array[0] = na1;
//        array[1] = na2;
//
//    }


    public static int[][] replaceElement(int[][] array, int elem, int[] newElem) {
        for (int i = 0; i < array.length; i++) {
            int elemsFound = 0;

            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == elem) {
                    elemsFound++;
                }
            }

            if (elemsFound == 0) {
                continue;
            }

            int[] newRow = new int[array[i].length + elemsFound * (newElem.length - 1)];

            int k = 0;
            int l = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == elem) {
                    for (int m = 0; m < newElem.length; m++) {
                        newRow[k] = newElem[m];
                        k++;
                    }
                    l++;
                } else {
                    newRow[k] = array[i][j];
                    k++;
                }
            }

            array[i] = newRow;
        }
        return array;
    }
}