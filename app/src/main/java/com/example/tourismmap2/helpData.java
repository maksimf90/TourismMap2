package com.example.tourismmap2;

public class helpData {
    String image, nameHelp, descriptionHelp;

    public helpData(String image, String nameHelp, String descriptionHelp) {
        this.image = image;
        this.nameHelp = nameHelp;
        this.descriptionHelp = descriptionHelp;
    }
    public helpData() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
