package net.gahfy.lazada500px.ui.photo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.MvpViewHolder;
import net.gahfy.lazada500px.ui.photo.presenter.PhotoItemPresenter;
import net.gahfy.lazada500px.utils.Provider;

/**
 * ViewHolder of a Category Item.
 * @author Gaëtan HERFRAY
 */
public class PhotoItemViewHolder extends MvpViewHolder<PhotoItemPresenter> implements PhotoItemView {
    /** The current item view */
    View itemView;
    /** The ImageView containing the preview of the photo */
    ImageView imgPhotoItemMain;
    /** The TextView containing the title of the photo */
    TextView lblPhotoItemTitle;
    /** The TextView containing the author of the photo*/
    TextView lblPhotoItemAuthor;

    /**
     * Instantiates a new MvpViewHolder.
     * @param itemView The view of the item
     */
    public PhotoItemViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        imgPhotoItemMain = (ImageView) itemView.findViewById(R.id.img_photo_item_main);
        lblPhotoItemTitle = (TextView) itemView.findViewById(R.id.lbl_photo_item_title);
        lblPhotoItemAuthor = (TextView) itemView.findViewById(R.id.lbl_photo_item_author);

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPhotoClicked();
            }
        });
    }

    @Override
    public void setPhoto(Context context, Photo photo) {
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(false);
        picasso.load(photo.getThumbImage().getHttpsUrl())
                .into(imgPhotoItemMain);
        lblPhotoItemTitle.setText(photo.getName());
        lblPhotoItemAuthor.setText(photo.getUser().getUsername());
    }

    @Override
    public void redirectToPhoto(BaseActivity parentActivity, Photo photo) {
        Bundle extras = new Bundle();
        extras.putString(PhotoActivity.EXTRA_NAME_PHOTO_JSON, Provider.getGson().toJson(photo));

        Intent photoIntent = new Intent(parentActivity, PhotoActivity.class);
        photoIntent.putExtras(extras);

        ActivityCompat.startActivity(parentActivity, photoIntent, null);
    }
}
