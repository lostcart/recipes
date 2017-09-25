package lost.cart.recipes.data.remote.recipes;

import java.util.List;

import lost.cart.recipes.data.local.models.Recipe;

/**
 * The response that we expect from the recipes service endpoint
 */
public class RecipesServiceResponse {
    private List<Recipe> results;

    public List<Recipe> getRecipes() {
        return results;
    }
}
