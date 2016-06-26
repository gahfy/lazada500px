package net.gahfy.lazada500px.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Image file of 500px
 * @author Gaëtan HERFRAY
 */
public class Image {
    /** The HTTPS url of the image */
    @SerializedName("https_url")
    private String httpsUrl;

    /**
     * The size of the image
     * @see <a href="https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes">Image URLs and Image Sizes</a>
     */
    @SuppressWarnings("unused")
    private int size;

    /**
     * Returns the HTTPS url of the image.
     * @return the HTTPS url of the image
     */
    public String getHttpsUrl() {
        return httpsUrl;
    }

    /**
     * Returns the size of the image.
     * @return the size of the image
     * @see <a href="https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes">Image URLs and Image Sizes</a>
     */
    public int getSize(){
        return size;
    }
}
