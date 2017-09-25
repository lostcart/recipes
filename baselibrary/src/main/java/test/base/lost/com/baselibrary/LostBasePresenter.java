package test.base.lost.com.baselibrary;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base presenter to be used with the base activity
 *
 * @param <V>
 */
public abstract class LostBasePresenter<V extends LostBasePresenter.View> {
    private CompositeDisposable compositeDisposable;
    private V view;

    @CallSuper
    public void onViewAttached(@NonNull final V view) {
        if (this.view != null) {
            throw new IllegalStateException("View " + view + " is already attached. Cannot attach " + view);
        }
        this.view = view;
    }

    @CallSuper
    public void onViewDetached() {
        if (view == null) {
            throw new IllegalStateException("View is already detached");
        }
        view = null;

        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public V getView() {
        return view;
    }

    @CallSuper
    protected void unsubscribeOnDetach(@NonNull final Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void onResume(){
    }

    public interface View {
    }
}
