package net.gahfy.lazada500px.ui.photo.presenter;

import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.BasePresenter;
import net.gahfy.lazada500px.ui.photo.view.PhotoItemView;

/**
 * The presenter for a photo item
 * @author Gaëtan HERFRAY
 */
public class PhotoItemPresenter extends BasePresenter<Photo, PhotoItemView> {
    /** The parent Activity */
    BaseActivity parentActivity;

    @Override
    protected void updateView() {
        view().setPhoto(parentActivity, model);
    }

    /**
     * Sets the parent Activity.
     * @param parentActivity the parent Activity to set.
     */
    public void setParentActivity(BaseActivity parentActivity){
        this.parentActivity = parentActivity;
    }

    /**
     * Called when a Photo is clicked
     */
    public void onPhotoClicked() {
        if (setupDone()) {
            view().redirectToPhoto(parentActivity, model);
        }
    }
}
