package com.code_help.GianlOof.recipe;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final static List<String> USAGE;

    static {
        final String[] list = new String[]{
            "list - lists the recipes",
            "stop - stops the program",
            "help - show commands",
            "find name - searches recipes by name",
            "find cooking time - searches recipes by cooking time",
            "find ingredient - searches recipes by ingredient"
        };
        USAGE = List.of(list);
    }

    private Scanner scanner;
    private RecipesCollection recipesCollection;

    public UserInterface(Scanner scanner, RecipesCollection recipesCollection) {
        this.scanner = scanner;
        this.recipesCollection = recipesCollection;
    }

    public void start() {
        System.out.print("File to read:");
        String fileName = scanner.nextLine();
        recipesCollection.fillCollectionFromFile(fileName);
        USAGE.forEach(System.out::println);
        String command = "";
        do {
            System.out.print("Enter command: ");
            command = scanner.nextLine();
            switch (command) {
                case "help" -> USAGE.forEach(System.out::println);
                case "list" -> this.recipesCollection.listRecipes();
                case "find name" -> {
                    System.out.print("Searched word:");
                    String name = scanner.nextLine();
                    System.out.println("Recipes:");
                    this.recipesCollection.findByName(name).forEach(System.out::println);
                }
                case "find cooking time" -> {
                    System.out.print("Max cooking time:");
                    int time = Integer.parseInt(scanner.nextLine());
                    System.out.println("Recipes:");
                    this.recipesCollection.findByCookingTime(time).forEach(System.out::println);
                }
                case "find ingredient" -> {
                    System.out.print("Ingredient:");
                    String ingredient = scanner.nextLine();
                    System.out.println("Recipes:");
                    this.recipesCollection.findByIngredient(ingredient).forEach(System.out::println);
                }
            }
        } while (!command.equals("stop"));
    }
}