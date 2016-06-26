package net.gahfy.lazada500px.data.remote;

import net.gahfy.lazada500px.data.model.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit service about photos
 * @author Gaëtan HERFRAY
 */
public interface PhotoService {

    /**
     * Retrieve the list of photos.
     * @param feature the feature GET parameter
     * @param only the only GET parameter
     * @param sort the sort GET parameter
     * @param page the page GET parameter
     * @param imageSize the image size GET parameter
     * @param consumerKey the consumer key GET parameter
     * @return Callback for web request
     * <a href="https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes">Image URLs and Image Sizes</a>
     */
    @GET("photos")
    Call<Page> listPhotos(@Query("feature") String feature,
                          @Query("only") String only,
                          @Query("sort") String sort,
                          @Query("page") int page,
                          @Query("image_size") String imageSize,
                          @Query("consumer_key") String consumerKey);
}
