package net.gahfy.lazada500px.ui.category.view;

import android.annotation.TargetApi;
import android.os.Build;

import net.gahfy.lazada500px.data.model.Category;

import java.util.Collection;

/**
 * Interface for the view of the list of Categories.
 * @author Gaëtan HERFRAY
 */
public interface CategoryListView {
    /**
     * Displays the categories in the view.
     * @param categories the categories to display in the view
     */
    void showCategories(Collection<Category> categories);

    /**
     * Displays the SnackBar with optional storage permission
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void showPermissionSnackbar();
}
