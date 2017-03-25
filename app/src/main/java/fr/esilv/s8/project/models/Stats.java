package fr.esilv.s8.project.models;

/**
 * Created by Harry on 25/03/2017.
 */

public class Stats {
    private String viewCount;
    private String likeCount;
    private String dislikeCount;
    private String favoriteCount;
    private String commentCount;


    public Stats(String viewCount, String likeCount, String dislikeCount, String favoriteCount, String commentCount) {
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }
}
