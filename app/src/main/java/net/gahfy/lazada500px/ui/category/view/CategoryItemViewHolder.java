package net.gahfy.lazada500px.ui.category.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.MvpViewHolder;
import net.gahfy.lazada500px.ui.category.presenter.CategoryItemPresenter;

/**
 * ViewHolder of a Category Item.
 * @author Gaëtan HERFRAY
 */
public class CategoryItemViewHolder extends MvpViewHolder<CategoryItemPresenter> implements CategoryItemView {
    /** The current item view */
    private final View itemView;

    /** The TextView containing the name of the category */
    private final TextView lblCategoryItemCategoryName;

    /**
     * Instantiates a new MvpViewHolder.
     * @param itemView The view of the item
     */
    public CategoryItemViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        lblCategoryItemCategoryName = (TextView) itemView.findViewById(R.id.lbl_category_item_category_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCategoryClicked();
            }
        });
    }

    @Override
    public void setCategoryName(String categoryName) {
        lblCategoryItemCategoryName.setText(categoryName);
    }

    @Override @SuppressWarnings("unchecked")
    public void redirectToCategory(BaseActivity parentActivity, Category category) {
        Bundle extras = new Bundle();
        extras.putInt(CategoryActivity.EXTRA_NAME_CATEGORY_ID, category.getCategoryId());

        Intent categoryIntent = new Intent(parentActivity, CategoryActivity.class);
        categoryIntent.putExtras(extras);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                parentActivity,
                new Pair<>(itemView.findViewById(R.id.lbl_category_item_category_name),
                        parentActivity.getString(R.string.transition_name_category_title))
        );
        ActivityCompat.startActivity(parentActivity, categoryIntent, options.toBundle());
    }
}
