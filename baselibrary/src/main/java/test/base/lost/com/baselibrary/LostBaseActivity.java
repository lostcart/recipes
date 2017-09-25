package test.base.lost.com.baselibrary;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Base activity to be used with the base presenter, handles creation and destruction
 *
 * @param <V>
 */
public abstract class LostBaseActivity<V extends LostBasePresenter> extends AppCompatActivity {
    private V basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initialiseView();
        basePresenter = initialisePresenter();
        if (basePresenter == null) {
            throw new RuntimeException("You must create a presenter in getPresenter");
        }
        basePresenter.onViewAttached((V.View) this);
    }

    @Override
    public void onDestroy() {
        basePresenter.onViewDetached();
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract V initialisePresenter();

    public V getPresenter() {
        return basePresenter;
    }

    protected abstract void initialiseView();

    @Override
    public void onResume(){
        super.onResume();
        basePresenter.onResume();
    }
}