package fr.esilv.s8.project.models;

import java.io.Serializable;

/**
 * Created by Harry on 21/03/2017.
 */

public class Size implements Serializable {
    private String url;

    public Size(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

