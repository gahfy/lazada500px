package net.gahfy.lazada500px.ui.category;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.MvpRecyclerListAdapter;
import net.gahfy.lazada500px.ui.category.presenter.CategoryItemPresenter;
import net.gahfy.lazada500px.ui.category.view.CategoryItemViewHolder;

/**
 * The Adapter of the list of Categories
 * @author Gaëtan HERFRAY
 */
public class CategoryListAdapter extends MvpRecyclerListAdapter<Category, CategoryItemPresenter, CategoryItemViewHolder> {
    /** The parent activity of the Adapter */
    BaseActivity parentActivity;

    /**
     * Instantiates a new CategoryListAdapter
     * @param parentActivity The parentActivity of the RecyclerView using this adapter
     */
    public CategoryListAdapter(BaseActivity parentActivity){
        super();
        this.parentActivity = parentActivity;
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @NonNull
    @Override
    protected CategoryItemPresenter createPresenter(@NonNull Category category) {
        CategoryItemPresenter presenter = new CategoryItemPresenter();
        presenter.setModel(category);
        presenter.setParentActivity(parentActivity);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Category model) {
        return model.getCategoryId();
    }
}
