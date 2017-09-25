package lost.cart.recipes.features.recipes;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import lost.cart.recipes.data.local.models.Recipe;
import lost.cart.recipes.features.base.BasePresenter;
import timber.log.Timber;

/**
 * Presenter for the recipes activity, handles view actions and performs actions to populate them
 */
public class RecipesPresenter extends BasePresenter<RecipesPresenter.View> {
    private static final long WAIT_TIME_BEFORE_SEARCH_MS = 500;

    @Override
    public void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);

        unsubscribeOnDetach(view.onSearchTermChangedObservable()
                .debounce(WAIT_TIME_BEFORE_SEARCH_MS, TimeUnit.MILLISECONDS).subscribe(
                success -> Timber.d("text changed"),
                error -> Timber.e("Something went wrong with text change")));
    }

    private void getRecipes(CharSequence searchTerm){

    }

    public interface View extends BasePresenter.View {
        Observable<CharSequence> onSearchTermChangedObservable();

        void showRecipes(List<Recipe> recipes);
        void showError(String error);
    }
}
