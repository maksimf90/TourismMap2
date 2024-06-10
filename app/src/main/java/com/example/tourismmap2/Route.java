package com.example.tourismmap2;

public  class Route {
    public String location;
    public String lvl;
    public String length;
    public String duration;
    public String description;
    public String thread;
    public String image;

    public Route() {
        // Пустой конструктор требуется для вызова DataSnapshot.getValue(Route.class)
    }

    public Route(String location, String lvl, String length, String duration, String description, String thread, String image) {
        this.location = location;
        this.lvl = lvl;
        this.length = length;
        this.duration = duration;
        this.description = description;
        this.thread = thread;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

