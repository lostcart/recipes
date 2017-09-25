package lost.cart.recipes.features.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lost.cart.recipes.R;
import lost.cart.recipes.data.local.models.Recipe;

/**
 * Adapter to display recipe items
 */
public class RecipesAdapater extends RecyclerView.Adapter<RecipesAdapater.RecipeViewHolder> {
    private List<Recipe> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recipes_item, parent, false));
    }
    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int position) {
        recipeViewHolder.setRecipe(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    protected class RecipeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recipes_item_textview_title) TextView titleTextView;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setRecipe(Recipe recipe) {
            titleTextView.setText(recipe.getTitle());
        }
    }
}
