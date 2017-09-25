package lost.cart.recipes.data.remote;

import lost.cart.recipes.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class to create a Retrofit Service
 */
public class Service {
    /**
     * Returns a Retrofit service with the given service class, setup with GSON and RxJava
     *
     * @param serviceClass
     * @param baseUrl
     * @param <T>
     * @return
     */
    private static <T> T getService(Class<T> serviceClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public static <T> T getService(Class<T> serviceClass) {
        return Service.getService(serviceClass, BuildConfig.BASE_URL);
    }
}
