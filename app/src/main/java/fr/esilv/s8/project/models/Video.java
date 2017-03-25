package fr.esilv.s8.project.models;

/**
 * Created by Harry on 25/03/2017.
 */

public class Video {
    private VideoItems items;

    public Video(VideoItems items) {
        this.items = items;
    }

    public VideoItems getVideoItems() {
        return items;
    }
}
