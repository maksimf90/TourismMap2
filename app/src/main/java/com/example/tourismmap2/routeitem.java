package com.example.tourismmap2;

public class routeitem {
    int image,id;
    String duration, rank, length, location, lvl, description, thread;

    public routeitem(int image, int id, String duration, String rank, String length, String location, String lvl, String description, String thread) {
        this.image = image;
        this.id = id;
        this.duration = duration;
        this.rank = rank;
        this.length = length;
        this.location = location;
        this.lvl = lvl;
        this.description = description;
        this.thread = thread;
    }



    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuration(String title) {
        this.duration = title;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setLvl(String lvl) { this.lvl = lvl; }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public String getRank() {
        return rank;
    }

    public String getLength() {
        return length;
    }

    public String getLocation() {
        return location;
    }
    public String getLvl() { return lvl; }
}

