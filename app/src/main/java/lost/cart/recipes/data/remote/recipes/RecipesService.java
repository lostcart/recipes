package lost.cart.recipes.data.remote.recipes;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service to retrieve recipes from the recipes endpoint
 */
public interface RecipesService {
    @GET("api")
    Single<RecipesServiceResponse> getRecipes(@Query("q") String searchTerm);
}
