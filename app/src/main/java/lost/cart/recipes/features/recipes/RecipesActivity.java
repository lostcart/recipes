package lost.cart.recipes.features.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;

import lost.cart.recipes.R;
import lost.cart.recipes.features.base.BaseActivity;
import test.base.lost.com.baselibrary.LostBasePresenter;

public class RecipesActivity extends BaseActivity implements RecipesPresenter.View {

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
        return new RecipesPresenter();
    }
    @Override
    protected void initialiseView() {
    }
}
