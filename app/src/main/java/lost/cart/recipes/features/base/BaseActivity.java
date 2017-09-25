package lost.cart.recipes.features.base;

import android.os.Bundle;
import android.view.MenuItem;

import test.base.lost.com.baselibrary.LostBaseActivity;
import test.base.lost.com.baselibrary.LostBasePresenter;

/**
 * Set any project specific activity settings here
 */
public abstract class BaseActivity<V extends LostBasePresenter> extends LostBaseActivity<V> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
