package com.example.tourismmap2;

public class helpitem {
    int image, id;
    String nameHelp, descriptionHelp;

    public helpitem(int image, int id, String nameHelp, String descriptionHelp) {
        this.image = image;
        this.id = id;
        this.descriptionHelp = descriptionHelp;
        this.nameHelp = nameHelp;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameHelp() {
        return nameHelp;
    }

    public void setNameHelp(String nameHelp) {
        this.nameHelp = nameHelp;
    }

    public String getDescriptionHelp() {
        return descriptionHelp;
    }

    public void setDescriptionHelp(String descriptionHelp) {
        this.descriptionHelp = descriptionHelp;
    }
}
