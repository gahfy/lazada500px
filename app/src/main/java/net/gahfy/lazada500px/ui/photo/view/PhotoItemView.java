package net.gahfy.lazada500px.ui.photo.view;

import android.content.Context;

import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;

/**
 * The view for a photo item
 * @author Gaëtan HERFRAY
 */
public interface PhotoItemView {
    /**
     * Displays the photo in the view.
     * @param context Context in which the application is working
     * @param photo the photo to display
     */
    void setPhoto(Context context, Photo photo);

    /**
     * Redirects the user to a Photo.
     * @param photo the Photo the user should be redirected to
     */
    void redirectToPhoto(BaseActivity parentActivity, Photo photo);
}
