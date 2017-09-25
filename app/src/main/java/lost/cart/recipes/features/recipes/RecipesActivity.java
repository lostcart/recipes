package lost.cart.recipes.features.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class RecipesActivity extends BaseActivity implements RecipesPresenter.View {
    @Bind(R.id.recipes_edittext_search) EditText searchEditText;
    @Bind(R.id.recipes_recyclerview_recipes) RecyclerView recipesRecyclerView;

    private RecipesAdapater recipesAdapater;

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
        recipesAdapater = new RecipesAdapater();
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recipesRecyclerView.setAdapter(recipesAdapater);
    }
    @Override
    public Observable<CharSequence> onSearchTermChangedObservable() {
        return RxTextView.textChanges(searchEditText);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesAdapater.setRecipes(recipes);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
