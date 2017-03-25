package fr.esilv.s8.project.models;

import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by Harry on 21/03/2017.
 */

public class Infos implements Serializable{
    private Snippet snippet;
    private Id id;

    public Infos(Snippet snippet, Id id) {
        this.snippet = snippet;
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public Id getId() {
        return id;
    }
}
