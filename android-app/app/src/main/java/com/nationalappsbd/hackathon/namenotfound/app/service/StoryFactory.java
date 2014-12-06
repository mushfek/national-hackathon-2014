package com.nationalappsbd.hackathon.namenotfound.app.service;

import com.google.android.gms.maps.model.LatLng;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bazlur on 12/6/14.
 */
public class StoryFactory {
    private static StoryFactory ourInstance = new StoryFactory();

    public static StoryFactory getInstance() {
        return ourInstance;
    }

    private List<Story> stories = new ArrayList<Story>();

    private StoryFactory() {
    }

    public List<Story> getStories() {
        return stories;
    }

    public void addStory(Story story) {
        stories.add(story);
    }

    public List<LatLng> getLocation() {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (Story story : stories) {
            latLngs.add(new LatLng(story.getLatitude(), story.getLongitude()));
        }

        return latLngs;
    }
}
