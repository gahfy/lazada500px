package net.gahfy.lazada500px.ui.photo.presenter;

import android.content.Context;

import com.google.gson.Gson;

import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BasePresenter;
import net.gahfy.lazada500px.ui.photo.view.PhotoView;
import net.gahfy.lazada500px.utils.AnimationEndListener;

/**
 * The presenter for a photo view
 * @author Gaëtan HERFRAY
 */
public class PhotoPresenter extends BasePresenter<Photo, PhotoView>{
    /** Context in which the application is running */
    private Context context;
    /** Whether the details view is currently being animated or not */
    private boolean isAnimatingDetails = false;
    /** Whether the details view is currently visible or not */
    private boolean isDetailsVisible = true;

    /**
     * Instantiates a new PhotoPresenter.
     * @param context Context in which the application is running.
     */
    public PhotoPresenter(Context context){
        this.context = context;
    }

    /**
     * Sets the model of the presenter.
     * @param photoJson The JSON representing the model object
     */
    public void setPhotoWithJson(String photoJson){
        model = new Gson().fromJson(photoJson, Photo.class);
    }

    @Override
    protected void updateView() {
        view().setPhoto(context, model);
    }

    /**
     * Called when a click is performed on the image.
     */
    public void onPhotoClick(){
        if(!isAnimatingDetails){
            isAnimatingDetails = true;
            AnimationEndListener animationEndListener = new AnimationEndListener() {
                @Override
                public void onAnimationEnd() {
                    isAnimatingDetails = false;
                    isDetailsVisible = !isDetailsVisible;
                }
            };
            if(isDetailsVisible)
                view().hideDetails(animationEndListener);
            else
                view().showDetails(animationEndListener);
        }
    }

    /**
     * Called when a photo successfully finished loading
     */
    public void onLoadingPhotoSuccess() {
        view().hideLoader(new AnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                view().showImage(null);
            }
        });
    }

    /**
     * Called when a photo failed to load
     */
    public void onLoadingPhotoFailed() {
        view().hideLoader(new AnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                view().showError(null);
            }
        });
    }

    /**
     * Called when the Retry button is clicked
     */
    public void onRetryClicked() {
        view().hideError(new AnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                view().showLoader(new AnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        view().setPhoto(context, model);
                    }
                });
            }
        });
    }
}
