package com.example.tourismmap2;

import java.util.ArrayList;

public class routeData {
    private String image;
    private String location;
    private String lvl;
    private String duration;
    private String length;
    private String description;
    private String thread;

    public routeData(String image, String location, String lvl, String duration, String length, String description, String thread) {
        this.image = image;
        this.location = location;
        this.lvl = lvl;
        this.duration = duration;
        this.length = length;
        this.description = description;
        this.thread = thread;
    }

    public routeData(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}




