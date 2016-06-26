package net.gahfy.lazada500px.ui.category.view;

import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BaseActivity;

/**
 * Interface for the view of a Category Item.
 * @author Gaëtan HERFRAY
 */
public interface CategoryItemView {
    /**
     * Displays the name of a Category.
     * @param categoryName The name to display
     */
    void setCategoryName(String categoryName);

    /**
     * Redirects the user to a Category.
     * @param category the Category the user should be redirected to
     */
    void redirectToCategory(BaseActivity parentActivity, Category category);
}
