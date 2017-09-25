package lost.cart.recipes.features.recipes;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lost.cart.recipes.data.local.models.Recipe;
import lost.cart.recipes.data.remote.recipes.RecipesHelper;
import lost.cart.recipes.features.base.BasePresenter;
import timber.log.Timber;

/**
 * Presenter for the recipes activity, handles view actions and performs actions to populate them
 */
public class RecipesPresenter extends BasePresenter<RecipesPresenter.View> {
    private static final long WAIT_TIME_BEFORE_SEARCH_MS = 500;

    private RecipesHelper recipesHelper;
    private Disposable disposable;

    public RecipesPresenter(RecipesHelper recipesHelper) {
        this.recipesHelper = recipesHelper;
    }

    @Override
    public void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);

        // Listen for changes to the search term and perform a search once the user
        // has stopped making changes for some time
        unsubscribeOnDetach(view.onSearchTermChangedObservable()
                .debounce(WAIT_TIME_BEFORE_SEARCH_MS, TimeUnit.MILLISECONDS).subscribe(
                        this::getRecipes,
                        error -> Timber.e("Something went wrong with text change")));
    }

    private void getRecipes(CharSequence searchTerm) {
        // Discard any previous searches that are happening
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        String searchString = searchTerm.toString();

        // Only search if the search term isn't empty
        if (!TextUtils.isEmpty(searchString)) {
            // Make a search with the given searchTerm
            disposable = recipesHelper.getRecipes(searchString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            results -> getView().showRecipes(results),
                            error -> {
                                String errorMessage = error.getLocalizedMessage();
                                getView().showError(errorMessage);
                                Timber.e("Error getting recipes :" + errorMessage);
                            }
                    );
            unsubscribeOnDetach(disposable);
        }
    }

    public interface View extends BasePresenter.View {
        Observable<CharSequence> onSearchTermChangedObservable();

        void showRecipes(List<Recipe> recipes);
        void showError(String error);
    }
}
