package com.nationalappsbd.hackathon.namenotfound.app.domain;

import java.io.Serializable;

/**
 * @author Mushfekur Rahman
 */
public class OpenThread implements Serializable {
    private int id;
    private String title;
    private String threadContent;
    private String postedBy;
    private String postDate;

    public OpenThread() {
    }

    public OpenThread(int id, String title, String threadBody) {
        this.id = id;
        this.title = title;
        this.threadContent = threadBody;
    }

    public OpenThread(int id, String title, String threadBody, String postedBy, String postDate) {
        this.id = id;
        this.title = title;
        this.threadContent = threadBody;
        this.postedBy = postedBy;
        this.postDate = postDate;
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

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
