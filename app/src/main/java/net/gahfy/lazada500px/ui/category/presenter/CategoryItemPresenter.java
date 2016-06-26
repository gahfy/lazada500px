package net.gahfy.lazada500px.ui.category.presenter;

import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.BasePresenter;
import net.gahfy.lazada500px.ui.category.view.CategoryItemView;

/**
 * The presenter for a category item in the list
 * @author Gaëtan HERFRAY
 */
public class CategoryItemPresenter extends BasePresenter<Category, CategoryItemView> {
    /** The parent Activity */
    BaseActivity parentActivity;

    @Override
    protected void updateView() {
        view().setCategoryName(model.getCategoryName());
    }

    /**
     * Sets the parent Activity.
     * @param parentActivity the parent Activity to set.
     */
    public void setParentActivity(BaseActivity parentActivity){
        this.parentActivity = parentActivity;
    }

    /**
     * Called when a Category is clicked
     */
    public void onCategoryClicked() {
        if (setupDone()) {
            view().redirectToCategory(parentActivity, model);
        }
    }
}
