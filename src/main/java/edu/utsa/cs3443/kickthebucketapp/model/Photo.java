package edu.utsa.cs3443.kickthebucketapp.model;

import java.time.LocalDate;

public class Photo {

    private String imagePath;
    private String caption;
    private final LocalDate dateAdded;

    public Photo(String imagePath, String caption) {
        this.imagePath = imagePath;
        this.caption = caption;
        this.dateAdded = LocalDate.now();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }
}
