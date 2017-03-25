package fr.esilv.s8.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 20/03/2017.
 */

public class Search implements Serializable{
    private Items items;

    public Search(Items items) {
        this.items = items;
    }

    public Items getItems() {
        return items;
    }
}
