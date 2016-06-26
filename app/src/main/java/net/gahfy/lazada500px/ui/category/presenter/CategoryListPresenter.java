package net.gahfy.lazada500px.ui.category.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.ui.base.BasePresenter;
import net.gahfy.lazada500px.ui.category.view.CategoryListView;

import java.util.Arrays;
import java.util.Collection;

/**
 * The presenter for the list of categories
 * @author Gaëtan HERFRAY
 */
public class CategoryListPresenter extends BasePresenter<Collection<Category>, CategoryListView>{
    /** Context in which the application is running */
    Context context;

    /**
     * Instantiates a new CategoryListPresenter.
     * @param context Context in which the application is running
     */
    public CategoryListPresenter(Context context){
        this.context = context;
    }

    @Override
    protected void updateView() {
        view().showCategories(model);
    }

    @Override
    public void bindView(@NonNull CategoryListView view) {
        if (model == null){
            initModel();
        }

        super.bindView(view);
    }

    /**
     * Initializes the model
     */
    private void initModel() {
        Category[] categoryArray = Category.getCategories(context);
        Arrays.sort(categoryArray);
        setModel(Arrays.asList(categoryArray));
    }
}
