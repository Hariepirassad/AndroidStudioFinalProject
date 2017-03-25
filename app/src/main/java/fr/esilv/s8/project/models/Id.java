package fr.esilv.s8.project.models;

import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by Harry on 21/03/2017.
 */

public class Id implements Serializable{
    private String videoId;

    public Id(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }
}
