package com.code_help.GianlOof.recipe;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipesCollection {
    private List<Recipe> recipes;

    public RecipesCollection() {
        this.recipes = new ArrayList<>();
    }

    public void fillCollectionFromFile(String fileName) {
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNextLine()) {
                String recipeName = scanner.nextLine();
                int preparationTime = Integer.parseInt(scanner.nextLine());
                ArrayList<String> ingredients = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String ingredient = scanner.nextLine();
                    if (ingredient.isEmpty()) {
                        break;
                    }
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(recipeName, preparationTime, ingredients);
                this.recipes.add(recipe);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listRecipes() {
        System.out.println("Recipes:");
        this.recipes.forEach(System.out::println);
    }

    public List<Recipe> findByName(String name) {
        ArrayList<Recipe> searchResult = new ArrayList<>();
        for (Recipe recipe :
            this.recipes) {
            if (recipe.name().contains(name)) {
                searchResult.add(recipe);
            }
        }
        return searchResult;
    }

    public List<Recipe> findByCookingTime(int time) {
        ArrayList<Recipe> searchResult = new ArrayList<>();
        for (Recipe recipe :
            this.recipes) {
            if (recipe.preparationTime() <= time) {
                searchResult.add(recipe);
            }
        }
        return searchResult;
    }

    public List<Recipe> findByIngredient(String ingredient) {
        ArrayList<Recipe> searchResult = new ArrayList<>();
        for (Recipe recipe :
            this.recipes) {
            if (recipe.ingredients().contains(ingredient)) {
                searchResult.add(recipe);
            }
        }
        return searchResult;
    }
}