package net.gahfy.lazada500px.ui.photo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.photo.presenter.PhotoPresenter;
import net.gahfy.lazada500px.utils.AnimationEndListener;
import net.gahfy.lazada500px.utils.AnimationUtils;

import javax.inject.Inject;

/**
 * The Activity which displays a photo full screen
 * @author Gaëtan HERFRAY
 */
public class PhotoActivity extends BaseActivity implements PhotoView {
    /** The name of the extra with the JSON representation of a photo */
    public static final String EXTRA_NAME_PHOTO_JSON = "photoJson";

    /** The main image (with full screen photo) */
    ImageView imgPhotoActivityMain;
    /** The retry button */
    Button btPhotoActivityRetry;
    /** The layout containing the main image */
    RelativeLayout lytPhotoActivityMain;
    /** The layout containing the details of an image (title and author) */
    RelativeLayout lytPhotoActivityDetails;
    /** The layout containing the loader */
    RelativeLayout lytPhotoActivityLoading;
    /** The layout containing the error */
    RelativeLayout lytPhotoActivityError;
    /** The TextView containing the title of the photo */
    TextView lblPhotoActivityTitle;
    /** The TextView containing the author of the photo */
    TextView lblPhotoActivityAuthor;

    @Inject
    PhotoPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgPhotoActivityMain = (ImageView) findViewById(R.id.img_photo_activity_main);
        lytPhotoActivityMain = (RelativeLayout) findViewById(R.id.lyt_photo_activity_main);
        lytPhotoActivityLoading = (RelativeLayout) findViewById(R.id.lyt_photo_activity_loading);
        lytPhotoActivityError = (RelativeLayout) findViewById(R.id.lyt_photo_activity_error);
        btPhotoActivityRetry = (Button) findViewById(R.id.bt_photo_activity_retry);
        lblPhotoActivityTitle = (TextView) findViewById(R.id.lbl_photo_activity_title);
        lblPhotoActivityAuthor = (TextView) findViewById(R.id.lbl_photo_activity_author);
        lytPhotoActivityDetails = (RelativeLayout) findViewById(R.id.lyt_photo_activity_details);

        btPhotoActivityRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRetryClicked();
            }
        });

        imgPhotoActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPhotoClick();
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras.containsKey(EXTRA_NAME_PHOTO_JSON)){
            presenter.setPhotoWithJson(extras.getString(EXTRA_NAME_PHOTO_JSON));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void setPhoto(Context context, Photo photo) {
        lblPhotoActivityTitle.setText(photo.getName());
        lblPhotoActivityAuthor.setText(photo.getUser().getUsername());
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(false);
        picasso.load(photo.getUncroppedBigImage().getHttpsUrl())
                .into(imgPhotoActivityMain, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onLoadingPhotoSuccess();
                    }

                    @Override
                    public void onError() {
                        presenter.onLoadingPhotoFailed();
                    }
                });
    }



    @Override
    public void showLoader(@Nullable final AnimationEndListener animationEndListener){
        AnimationUtils.fadeInView(this, lytPhotoActivityLoading, animationEndListener);
    }

    @Override
    public void hideLoader(@Nullable final AnimationEndListener animationEndListener){
        AnimationUtils.fadeOutView(this, lytPhotoActivityLoading, animationEndListener);
    }

    @Override
    public void showError(@Nullable final AnimationEndListener animationEndListener) {
        AnimationUtils.fadeInView(this, lytPhotoActivityError, animationEndListener);
    }

    @Override
    public void hideError(@Nullable final AnimationEndListener animationEndListener) {
        AnimationUtils.fadeOutView(this, lytPhotoActivityError, animationEndListener);
    }

    @Override
    public void showImage(@Nullable final AnimationEndListener animationEndListener) {
        AnimationUtils.fadeInView(this, lytPhotoActivityMain, animationEndListener);
    }

    @Override
    public void showDetails(@Nullable AnimationEndListener animationEndListener) {
        AnimationUtils.fadeInView(this, lytPhotoActivityDetails, animationEndListener);
    }

    @Override
    public void hideDetails(@Nullable AnimationEndListener animationEndListener) {
        AnimationUtils.fadeOutView(this, lytPhotoActivityDetails, animationEndListener);
    }
}
