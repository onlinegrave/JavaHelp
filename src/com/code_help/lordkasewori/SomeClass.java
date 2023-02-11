package com.code_help.lordkasewori;

import java.util.List;

public class SomeClass {
    public static String splitByComma(List<String> list) {
        StringBuilder str = new StringBuilder("");
        // Traversing the ArrayList
        for (String eachstring : list) {
            // Each element in ArrayList is appended
            // followed by comma
            str.append(eachstring).append(", ");
        }
        // StringBuffer to String conversion
        String commaseparatedlist = str.toString();

        // Condition check to remove the last comma
        if (commaseparatedlist.length() > 0)
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 2);
        return commaseparatedlist;
    }
}
