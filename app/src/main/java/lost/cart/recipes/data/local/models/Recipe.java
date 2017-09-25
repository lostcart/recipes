package lost.cart.recipes.data.local.models;

import com.google.gson.annotations.SerializedName;

/**
 * A recipe object
 * <p>
 * TODO: create a local recipe object and a remote recipe object so they're not tied together
 */
public class Recipe {
    @SerializedName("title")
    private String title;
    @SerializedName("ingredients")
    private String ingredients;
    @SerializedName("thumbnail")
    private String imageUrl;
    @SerializedName("href")
    private String linkUrl;

    public String getTitle() {
        return title;
    }
    public String getIngredients() {
        return ingredients;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getLinkUrl() {
        return linkUrl;
    }
}
