package com.code_help.GianlOof.recipe;

import java.util.Scanner;

public class RecipeSearch {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RecipesCollection recipesCollection = new RecipesCollection();
        UserInterface ui = new UserInterface(scanner, recipesCollection);
        ui.start();
    }
}
