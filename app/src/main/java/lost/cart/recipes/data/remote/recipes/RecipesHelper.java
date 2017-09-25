package lost.cart.recipes.data.remote.recipes;

import java.util.List;

import io.reactivex.Single;
import lost.cart.recipes.data.local.models.Recipe;
import lost.cart.recipes.data.remote.Service;

/**
 * Helper class to retrieve a list of recipes from the recipes service
 */
public class RecipesHelper {

    /**
     * Helper method to grab a list of results from the recipe service and return
     * a list of recipe items
     *
     * @param keyword
     * @return
     */
    public Single<List<Recipe>> getRecipes(String keyword) {
        RecipesService recipeService = Service.getService(RecipesService.class);
        return Single.create(singleEmitter ->
                recipeService.getRecipes(keyword).subscribe(
                        result -> {
                            if (result.getRecipes() != null) {
                                singleEmitter.onSuccess(result.getRecipes());
                            } else {
                                singleEmitter.onError(new Error("No recipes found"));
                            }
                        },
                        error -> singleEmitter.onError(new Error(error))));
    }
}
