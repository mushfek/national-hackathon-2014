package com.nationalappsbd.hackathon.namenotfound.app.service;

import com.google.android.gms.maps.model.LatLng;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public List<LatLng> getFilteredLocation(String filterKey) {
        List<LatLng> latLngs = new ArrayList<LatLng>();

        for (Story story : getStories()) {
            if (story.getCategory().trim().equals(filterKey.trim())) {
                latLngs.add(new LatLng(story.getLatitude(), story.getLongitude()));
            }
        }

        return latLngs;
    }

    public Story findById(Long id) {
        for (Story story : stories) {
            if (story.getId().equals(id)) {
                return story;
            }
        }

        return null;
    }

    static {
        String[] category = {
                "Abuse",
                "Eve teasing",
                "Assault",
                "Groping",
                "Stalking",
                "Other"
        };

        Random random = new Random();
        int categoryId = random.nextInt(category.length - 1);

        Story story = new Story();
        story.setId(1l);
        story.setCategory(category[categoryId]);
        story.setAddress("Dhaka");
        story.setLatitude(23.7115253);
        story.setLongitude(90.4111451);
        story.setPledge("I experienced this");
        story.setDate(new Date());
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);

        categoryId = random.nextInt(category.length - 1);
        story = new Story();
        story.setId(2l);
        story.setCategory(category[categoryId]);
        story.setAddress("Faridpur");
        story.setLatitude(23.6070822);
        story.setLongitude(89.8429406);
        story.setPledge("I saw this");
        story.setDate(new Date());
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);

        categoryId = random.nextInt(category.length - 1);
        story = new Story();
        story.setId(3l);
        story.setCategory(category[categoryId]);
        story.setAddress("Gazipur");
        story.setLatitude(24.0022858);
        story.setLongitude(90.4264283);
        story.setPledge("I saw this");
        story.setDate(new Date());
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);

        categoryId = random.nextInt(category.length - 1);
        story = new Story();
        story.setId(4l);
        story.setCategory(category[categoryId]);
        story.setAddress("Gopalganj");
        story.setLatitude(23.0050857);
        story.setLongitude(89.8266059);
        story.setPledge("I experienced this");
        story.setDate(new Date());
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);

        categoryId = random.nextInt(category.length - 1);
        story = new Story();
        story.setId(5l);
        story.setCategory(category[categoryId]);
        story.setAddress("Kishoreganj");
        story.setLatitude(24.444937);
        story.setLongitude(90.776575);
        story.setDate(new Date());
        story.setPledge("I experienced this");
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);

        categoryId = random.nextInt(category.length - 1);
        story = new Story();
        story.setId(6l);
        story.setCategory(category[categoryId]);
        story.setAddress("Madaripur");
        story.setLatitude(23.164102);
        story.setLongitude(90.1896805);
        story.setPledge("I experienced this");
        story.setDate(new Date());
        story.setStory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ut egestas felis. Cras orci nunc, porttitor eget mattis quis, porttitor a nunc. Aenean id dui ex. Duis tempor tortor in hendrerit accumsan. Vivamus in est vel ante cursus ultrices consequat at eros. Integer placerat ac nibh mollis tempor. Cras ac massa vel mi faucibus pretium eu vel sapien. ");
        getInstance().addStory(story);


    /* Dhaka',       23.7115253, 90.4111451
     Faridpur',    23.6070822, 89.8429406,
     Gazipur',             24.0022858, 90.4264283,
     Gopalganj',           23.0050857, 89.8266059,
     Jamalpur',            24.937533, 89.937775,
     Kishoreganj           24.444937, 90.776575,
     Madaripur',           23.164102, 90.1896805,

     */


    }

    public List<Story> getFilteredStories(String filterKey) {

        List<Story> stories = new ArrayList<Story>();

        for (Story story : getStories()) {
            if (story.getCategory().trim().equals(filterKey.trim())) {
                stories.add(story);
            }
        }

        return stories;
    }
}
