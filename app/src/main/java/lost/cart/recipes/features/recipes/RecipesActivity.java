package lost.cart.recipes.features.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import butterknife.Bind;
import io.reactivex.Observable;
import lost.cart.recipes.R;
import lost.cart.recipes.data.local.models.Recipe;
import lost.cart.recipes.data.remote.recipes.RecipesHelper;
import lost.cart.recipes.features.base.BaseActivity;
import test.base.lost.com.baselibrary.LostBasePresenter;
import timber.log.Timber;

public class RecipesActivity extends BaseActivity implements RecipesPresenter.View {
    @Bind(R.id.recipes_edittext_search) EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.recipes;
    }
    @NonNull
    @Override
    protected LostBasePresenter initialisePresenter() {
        return new RecipesPresenter(new RecipesHelper());
    }
    @Override
    protected void initialiseView() {
    }
    @Override
    public Observable<CharSequence> onSearchTermChangedObservable() {
        return RxTextView.textChanges(searchEditText);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        Timber.d(recipes.toString());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
