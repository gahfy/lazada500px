package net.gahfy.lazada500px.ui.category.presenter;

import android.content.Context;

import net.gahfy.lazada500px.R;
import net.gahfy.lazada500px.data.model.Category;
import net.gahfy.lazada500px.data.model.Page;
import net.gahfy.lazada500px.data.model.Photo;
import net.gahfy.lazada500px.data.remote.PhotoServiceCalls;
import net.gahfy.lazada500px.ui.base.BasePresenter;
import net.gahfy.lazada500px.ui.category.view.CategoryView;
import net.gahfy.lazada500px.utils.AnimationEndListener;

import java.util.Arrays;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The presenter for the category
 * @author Gaëtan HERFRAY
 */
public class CategoryPresenter extends BasePresenter<Category, CategoryView> {
    /** Context in which the application is working */
    private Context context;
    /** The current page displayed in the RecyclerView */
    private int currentPage = 0;
    /** The total page number of data */
    private int totalPages = 0;
    /** Whether we are currently loading a page or not */
    private boolean isLoading = false;

    /**
     * Instantiates a new CategoryPresenter.
     * @param context Context in which the application is running
     */
    public CategoryPresenter(Context context){
        this.context = context;
    }

    @Override
    protected void updateView() {
        if(totalPages ==0) {
            view().showCategoryTitle(model);
            view().showLoader(new AnimationEndListener() {
                @Override
                public void onAnimationEnd() {
                    loadNextPage();
                }
            });
        }
    }

    /**
     * Sets the model of the presenter
     * @param categoryId the unique identifier of the Category to set as model of the presenter
     */
    public void setCategoryWithId(int categoryId){
        Category[] categories = Category.getCategories(context);
        if(categoryId < categories.length){
            setModel(categories[categoryId]);
        }
    }

    /**
     * Called when the button Retry is clicked
     */
    public void onRetryClicked() {
        if (setupDone()) {
            view().hideInternetError(new AnimationEndListener() {
                @Override
                public void onAnimationEnd() {
                    view().showLoader(new AnimationEndListener() {
                        @Override
                        public void onAnimationEnd() {
                            loadNextPage();
                        }
                    });
                }
            });
        }
    }

    /**
     * Called when the bottom of the RecyclerView is reached
     */
    public void onScrollBottomReached(){
        if(currentPage < totalPages && !isLoading){
            loadNextPage();
        }
    }

    /**
     * Loads the next page
     */
    public void loadNextPage(){
        isLoading = true;
        currentPage++;
        int thumbSize = context.getResources().getInteger(R.integer.size_px_thumb);
        Call<Page> call = PhotoServiceCalls.getPage(model.getCategoryCodeName(), currentPage, thumbSize);
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, final Response<Page> response) {
                onLoadingPageSuccess(response);
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                onLoadingPageFailed();
            }
        });
    }

    /**
     * Called when loading page successfully ended.
     * @param response the response retrieved from the server
     */
    public void onLoadingPageSuccess(final Response<Page> response){
        isLoading = false;
        view().hideLoader(new AnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                Collection<Photo> photos = Arrays.asList(response.body().getPhotos());
                // If nothing has been previously loaded
                if(totalPages==0)

                {
                    totalPages = response.body().getTotalPages();
                    view().showPhotos(photos);
                }
                // Else (if things has been previously loaded)
                else

                {
                    view().addPhotos(photos);
                }
            }
        });
    }

    /**
     * Called when loading page failed to end
     */
    public void onLoadingPageFailed(){
        isLoading = false;
        // Decrement to give an other try when the user will scroll to the bottom again
        currentPage--;
        // If nothing has been previously loaded
        if(totalPages == 0){
            view().hideLoader(new AnimationEndListener() {
                @Override
                public void onAnimationEnd() {
                    view().showInternetError(null);
                }
            });
        }
    }
}
