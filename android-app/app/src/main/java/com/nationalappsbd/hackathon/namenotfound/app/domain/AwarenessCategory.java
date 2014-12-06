package com.nationalappsbd.hackathon.namenotfound.app.domain;

import java.io.Serializable;

/**
 * @author Mushfekur Rahman
 */
public class AwarenessCategory implements Serializable {

    private int id;
    private String title;

    public AwarenessCategory() {
    }

    public AwarenessCategory(int id, String categoryName) {
        this.id = id;
        this.title = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
