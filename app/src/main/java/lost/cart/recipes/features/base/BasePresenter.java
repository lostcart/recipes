package lost.cart.recipes.features.base;


import test.base.lost.com.baselibrary.LostBasePresenter;

/**
 * Set any project specific settings for the presenters here
 */
public abstract class BasePresenter<V extends LostBasePresenter.View> extends LostBasePresenter<V> {

    public interface View extends LostBasePresenter.View {
    }
}
