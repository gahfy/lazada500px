package net.gahfy.lazada500px.injection.module;

import net.gahfy.lazada500px.injection.scope.PerActivity;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.category.CategoryListAdapter;
import net.gahfy.lazada500px.ui.category.presenter.CategoryListPresenter;
import net.gahfy.lazada500px.ui.category.presenter.CategoryPresenter;
import net.gahfy.lazada500px.ui.category.view.CategoryActivity;
import net.gahfy.lazada500px.ui.category.view.CategoryListActivity;
import net.gahfy.lazada500px.ui.photo.PhotoListAdapter;
import net.gahfy.lazada500px.ui.photo.presenter.PhotoPresenter;
import net.gahfy.lazada500px.ui.photo.view.PhotoActivity;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * This module injects the needed properties to an Activity
 * @author Gaëtan HERFRAY
 */
@Module(
    injects = {
        CategoryListActivity.class,
        CategoryActivity.class,
        PhotoActivity.class
    }
)
public class ActivityModule {
    /** The reference to the BaseActivity */
    private WeakReference<BaseActivity> baseActivity;

    /**
     * Instantiates a new ActivityModule.
     * @param baseActivity the BaseActivity of the module
     */
    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = new WeakReference<>(baseActivity);
    }

    /**
     * Returns the base activity
     * @return the base activity
     */
    @Provides @PerActivity @SuppressWarnings("unused")
    public BaseActivity provideBaseActivity() {
        return baseActivity.get();
    }

    /**
     * Returns the CategoryListAdapter.
     * @param baseActivity the base activity
     * @return the CategoryListAdapter
     */
    @Provides @PerActivity  @SuppressWarnings("unused")
    public CategoryListAdapter provideCategoryListAdapter(BaseActivity baseActivity){
        return new CategoryListAdapter(baseActivity);
    }

    /**
     * Returns the PhotoListAdapter.
     * @param baseActivity the base activity
     * @return the PhotoListAdapter
     */
    @Provides @PerActivity @SuppressWarnings("unused")
    public PhotoListAdapter providePhotoListAdapter(BaseActivity baseActivity){
        return new PhotoListAdapter(baseActivity);
    }

    /**
     * Returns the CategoryListPresenter.
     * @param baseActivity the base activity
     * @return the CategoryListPresenter
     */
    @Provides @PerActivity  @SuppressWarnings("unused")
    public CategoryListPresenter provideCategorListyPresenter(BaseActivity baseActivity){
        return new CategoryListPresenter(baseActivity);
    }

    /**
     * Returns the CategoryPresenter.
     * @param baseActivity the base activity
     * @return the CategoryPresenter
     */
    @Provides @PerActivity  @SuppressWarnings("unused")
    public CategoryPresenter provideCategoryPresenter(BaseActivity baseActivity){
        return new CategoryPresenter(baseActivity);
    }

    @Provides @PerActivity @SuppressWarnings("unused")
    public PhotoPresenter providePhotoPresenter(BaseActivity baseActivity){
        return new PhotoPresenter(baseActivity);
    }
}
