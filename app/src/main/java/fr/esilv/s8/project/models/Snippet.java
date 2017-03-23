package fr.esilv.s8.project.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 20/03/2017.
 */

public class Snippet {
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private Date publishedAt;
    private String channelTitle;


    public Snippet(String title, String description, Thumbnails thumbnails, Date publishedAt, String channelTitle) {
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.publishedAt = publishedAt;
        this.channelTitle = channelTitle;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }
}
