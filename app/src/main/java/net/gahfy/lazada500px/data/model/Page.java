package net.gahfy.lazada500px.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Page of 500px
 * @author Gaëtan HERFRAY
 */
public class Page {
    /** The total page number */
    @SerializedName("total_pages")
    private int totalPages;

    /** The photos in the page */
    @SuppressWarnings("unused")
    private Photo[] photos;

    /**
     * Returns the total page number.
     * @return the total page number
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Returns the photos in the page.
     * @return the photos in the page
     */
    public Photo[] getPhotos() {
        return photos;
    }
}
