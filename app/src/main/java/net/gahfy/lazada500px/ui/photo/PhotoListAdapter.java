package net.gahfy.lazada500px.ui.photo;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.base.MvpRecyclerListAdapter;
import net.gahfy.lazada500px.ui.photo.presenter.PhotoItemPresenter;
import net.gahfy.lazada500px.ui.photo.view.PhotoItemViewHolder;

/**
 * The Adapter of the list of Photos
 * @author Gaëtan HERFRAY
 */
public class PhotoListAdapter extends MvpRecyclerListAdapter<Photo, PhotoItemPresenter, PhotoItemViewHolder> {
    /** The parent activity of the Adapter */
    BaseActivity parentActivity;

    @Override
    public PhotoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false));
    }

    /**
     * Instantiates a new PhotoListAdapter
     * @param parentActivity The parentActivity of the RecyclerView using this adapter
     */
    public PhotoListAdapter(BaseActivity parentActivity){
        super();
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    protected PhotoItemPresenter createPresenter(@NonNull Photo photo) {
        PhotoItemPresenter presenter = new PhotoItemPresenter();
        presenter.setModel(photo);
        presenter.setParentActivity(parentActivity);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Photo model) {
        return model.getId();
    }
}
