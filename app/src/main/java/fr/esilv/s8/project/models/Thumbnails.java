package fr.esilv.s8.project.models;

import java.io.Serializable;

/**
 * Created by Harry on 20/03/2017.
 */

public class Thumbnails implements Serializable {
    private Size medium;

    public Thumbnails(Size medium) {
        this.medium = medium;
    }

    public Size getMedium() {
        return medium;
    }
}
