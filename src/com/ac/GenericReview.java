package com.ac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class GenericReview {

    public static <T> List<T> filter(List<T> values, Predicate<? super T> p) {
        List<T> lst = new ArrayList<>();
        for (T t : values) {
            if (p.test(t)) {
                lst.add(t);
            }
        }
        return lst;
    }

    public static <T, R> List<R> map(List<T> values, Function<T, R> f) {
        List<R> lst = new ArrayList<>();
        for (T t : values) {
            lst.add(f.apply(t));
        }
        return lst;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(Arrays.asList("prognosticator", "welcome", "agreement", "quintessence", "kaleidoscopic"));
        System.out.println(strings.stream().filter(s -> s.length() > 10).collect(Collectors.toList()));
        System.out.println(filter(strings, s -> s.length() > 10));

        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("Larry", "Curly", "Moe", "Shemp", "Curly Joe"));
        System.out.printf("filter(%s, value -> value.length() == 3) returns %s\n", list1, filter(list1, value -> value.length() == 3));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.printf("filter(%s, value -> value %% 2 != 0 ) returns %s\n", list2, filter(list2, value -> value % 2 != 0));
//
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("Huey", "Dewey", "Louie"));
//        System.out.printf("map(%s, Week9Program::reverseString) returns %s\n", list1, map(list3, Week9Program::reverseString));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList("bob", "carol", "ted", "alice"));
//        System.out.printf("map(%s, value -> value + " @mtu.edu
//        " ) returns %s\n", list4, map(list4, value -> value + "@mtu.edu"));
        ArrayList<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.printf("map(%s, value -> Math.pow(2, value) ) returns %s\n", list5, map(list5, value -> Math.pow(2, value)));


    }

}


