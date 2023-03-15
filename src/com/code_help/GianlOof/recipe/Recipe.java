package com.code_help.GianlOof.recipe;

import java.util.List;
import java.util.Objects;

public record Recipe(String name, int preparationTime, List<String> ingredients) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return preparationTime == recipe.preparationTime && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients);
    }


}