package net.gahfy.lazada500px.data.remote;

import net.gahfy.lazada500px.data.model.Page;
import net.gahfy.lazada500px.utils.Provider;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Calls of Retrofit Photo services
 * @author Gaëtan HERFRAY
 */
public class PhotoServiceCalls {
    /** The consumer key of the application */
    private static final String CONSUMER_KEY = "Vf3iYtKEHZEghoTiIxxCOUKpIRSXEK7HkNwmbVSv";
    /** The default sort option */
    private static final String SORT_BY_CREATION = "created_at";
    /** The default filter */
    private static final String RECENT_FEATURE = "fresh_today";

    /**
     * Calls the page service.
     * @param categoryName The name of the category for which to get the pages
     * @param page the number of the page to retrieve
     * @param previewSize the size of the photos in the page
     * @return Callback for web request
     * @see <a href="https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes">Image URLs and Image Sizes</a>
     */
    public static Call<Page> getPage(String categoryName, int page, int previewSize){
        Retrofit retrofit = Provider.getRetrofit();
        PhotoService service = retrofit.create(PhotoService.class);

        String previewSizeParameter = String.format(Locale.US, "%d,2048", previewSize);
        return service.listPhotos(RECENT_FEATURE, categoryName, SORT_BY_CREATION, page, previewSizeParameter, CONSUMER_KEY);
    }
}
