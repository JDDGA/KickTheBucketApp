package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;

/**
 * stores info for a photo inside the photo album
 */
public class Photo {

    private String imagePath;
    private String caption;
    private final LocalDate dateAdded;

    /**
     * constructor
     * creates new photo
     */
    public Photo(String imagePath, String caption) {
        this.imagePath = imagePath;
        this.caption = caption;
        this.dateAdded = LocalDate.now();
    }

    /**
     * GETTER
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * SETTER
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * GETTER
     */
    public String getCaption() {
        return caption;
    }

    /**
     * SETTER
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * GETTER
     */
    public LocalDate getDateAdded() {
        return dateAdded;
    }
}
