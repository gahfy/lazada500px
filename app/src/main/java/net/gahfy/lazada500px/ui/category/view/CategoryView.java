package net.gahfy.lazada500px.ui.category.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.utils.AnimationEndListener;

import java.util.Collection;

/**
 * Interface for the view of the list of photos of a Category.
 * @author Gaëtan HERFRAY
 */
public interface CategoryView {
    /**
     * Displays the title of the Category
     * @param category the category for which to display the title
     */
    void showCategoryTitle(@NonNull Category category);

    /**
     * Shows the loader
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showLoader(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Hides the loader
     * @param animationEndListener to perform actions at the end of the animation
     */
    void hideLoader(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Shows the internet error
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showInternetError(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Hides the internet error
     * @param animationEndListener to perform actions at the end of the animation
     */
    void hideInternetError(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Shows photo in the RecyclerView (to be called once at first time)
     * @param photos the list of photos to show
     */
    void showPhotos(@NonNull Collection<Photo> photos);

    /**
     * Adds photos in the RecyclerView (to be called when there is already photos in the RecyclerView)
     * @param photos the list of photos to add
     */
    void addPhotos(@NonNull Collection<Photo> photos);
}
