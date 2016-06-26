package net.gahfy.lazada500px.ui.category.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.ui.base.BaseActivity;
import net.gahfy.lazada500px.ui.category.presenter.CategoryPresenter;
import net.gahfy.lazada500px.ui.photo.PhotoListAdapter;
import net.gahfy.lazada500px.utils.AnimationEndListener;
import net.gahfy.lazada500px.utils.AnimationUtils;

import java.util.Collection;

import javax.inject.Inject;

/**
 * The activity with the list of photos of a Category
 * @author Gaëtan HERFRAY
 */
public class CategoryActivity extends BaseActivity implements CategoryView {
    /** The name of the extra with identifier of category */
    public static final String EXTRA_NAME_CATEGORY_ID = "categoryId";

    /** The title of the Category */
    TextView lblCategoryTitle;
    /** The RecyclerView with the photos */
    RecyclerView rvCategoryPhotos;
    /** The layout containing the layout progress bar */
    RelativeLayout lytCategoryLoading;
    /** The layout containing the internet error */
    RelativeLayout lytCategoryError;
    /** The Retry button (for internet error) */
    Button btCategoryErrorRetry;
    /** The progressbar when loading the list of photos */
    ProgressBar pbCategoryLoadingPhoto;

    /** The presenter of the Activity */
    @Inject
    CategoryPresenter presenter;

    /** The adapter of the RecyclerView with photos*/
    @Inject
    PhotoListAdapter photoItemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lblCategoryTitle = (TextView) findViewById(R.id.lbl_category_title);
        lytCategoryError = (RelativeLayout) findViewById(R.id.lyt_category_error);
        btCategoryErrorRetry = (Button) findViewById(R.id.bt_category_error_retry);
        lytCategoryLoading = (RelativeLayout) findViewById(R.id.lyt_category_loading);
        pbCategoryLoadingPhoto = (ProgressBar) findViewById(R.id.pb_category_loading_photo);
        rvCategoryPhotos = (RecyclerView) findViewById(R.id.rv_category_photos);

        int accentColor = ContextCompat.getColor(this, R.color.accent);
        pbCategoryLoadingPhoto.getIndeterminateDrawable().setColorFilter(accentColor, android.graphics.PorterDuff.Mode.MULTIPLY);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategoryPhotos.setHasFixedSize(true);
        rvCategoryPhotos.setLayoutManager(layoutManager);
        rvCategoryPhotos.setAdapter(photoItemAdapter);

        rvCategoryPhotos.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (recyclerView.getAdapter().getItemCount() != 0) {
                    int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                        presenter.onScrollBottomReached();
                    }
                }
            }
        });

        btCategoryErrorRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRetryClicked();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras.containsKey(EXTRA_NAME_CATEGORY_ID)){
            presenter.setCategoryWithId(extras.getInt(EXTRA_NAME_CATEGORY_ID));
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
    public void showCategoryTitle(@NonNull Category category) {
        lblCategoryTitle.setText(category.getCategoryName());
    }

    @Override
    public void showLoader(@Nullable final AnimationEndListener animationEndListener){
        AnimationUtils.fadeInView(this, lytCategoryLoading, animationEndListener);
    }

    @Override
    public void hideLoader(@Nullable final AnimationEndListener animationEndListener){
        AnimationUtils.fadeOutView(this, lytCategoryLoading, animationEndListener);
    }

    @Override
    public void showInternetError(@Nullable final AnimationEndListener animationEndListener) {
        AnimationUtils.enterView(this, lytCategoryError, animationEndListener);
    }

    @Override
    public void hideInternetError(@Nullable final AnimationEndListener animationEndListener) {
        AnimationUtils.exitView(this, lytCategoryError, animationEndListener);
    }

    @Override
    public void showPhotos(@NonNull Collection<Photo> photos) {
        photoItemAdapter.clearAndAddAll(photos);
    }

    @Override
    public void addPhotos(@NonNull Collection<Photo> photos) {
        photoItemAdapter.addAll(photos);
    }
}
