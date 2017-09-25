package test.base.lost.com.baselibrary;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

public abstract class LostBaseLinearLayout<V extends LostBasePresenter> extends LinearLayout {
    private V basePresenter;

    public LostBaseLinearLayout(Context context) {
        this(context, null);
    }

    public LostBaseLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LostBaseLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutId(), this);
        ButterKnife.bind(this);
        initialiseView();
        basePresenter = initialisePresenter();
        if (basePresenter == null) {
            throw new RuntimeException("You must create a presenter in getPresenter");
        }
        basePresenter.onViewAttached((V.View) this);
    }

    public void onViewDetached() {
        basePresenter.onViewDetached();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract V initialisePresenter();

    public V getPresenter() {
        return basePresenter;
    }

    protected abstract void initialiseView();
}