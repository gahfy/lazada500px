package net.gahfy.lazada500px.data.model;

/**
 * Photo of 500px
 * @author Gaëtan HERFRAY
 */
public class Photo {
    /** The unique identifier of the photo */
    @SuppressWarnings("unused")
    private int id;
    /** The name of the photo */
    @SuppressWarnings("unused")
    private String name;
    /** The image files of the photo */
    @SuppressWarnings({"unused", "MismatchedReadAndWriteOfArray"})
    private Image[] images;
    /** The user of the photo */
    @SuppressWarnings("unused")
    private User user;

    /**
     * Returns the unique identifier of the photo.
     * @return tht unique identifier of the photo
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the name of the photo.
     * @return the name of the photo
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user of the photo.
     * @return the user of the photo
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the thumb image.
     * @return the thumb image
     */
    public Image getThumbImage(){
        for(Image image:images){
            if(image.getSize() != 2048)
                return image;
        }
        return null;
    }

    /**
     * Returns the uncropped big image.
     * @return the uncropped big image
     */
    public Image getUncroppedBigImage(){
        for(Image image:images){
            if(image.getSize() == 2048)
                return image;
        }
        return null;
    }
}
