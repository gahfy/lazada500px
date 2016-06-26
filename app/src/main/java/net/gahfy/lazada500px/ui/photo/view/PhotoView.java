package net.gahfy.lazada500px.ui.photo.view;

import android.content.Context;
import android.support.annotation.Nullable;

import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.utils.AnimationEndListener;

/**
 * Interface for the view of a full screen photo.
 * @author Gaëtan HERFRAY
 */
public interface PhotoView {
    /**
     * Displays the photo and its details.
     * @param context Context in which the application is running
     * @param photo The photo to display
     */
    void setPhoto(Context context, Photo photo);

    /**
     * Displays the loader.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showLoader(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Hides the loader.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void hideLoader(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Displays the error.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showError(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Hides the error.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void hideError(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Displays the image.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showImage(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Shows the details.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void showDetails(@Nullable final AnimationEndListener animationEndListener);

    /**
     * Hides the details.
     * @param animationEndListener to perform actions at the end of the animation
     */
    void hideDetails(@Nullable final AnimationEndListener animationEndListener);
}
