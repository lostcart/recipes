package lost.cart.recipes;

import android.app.Application;

import timber.log.Timber;

public class RecipesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
