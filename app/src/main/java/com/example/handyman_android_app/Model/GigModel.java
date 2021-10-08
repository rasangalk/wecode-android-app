package com.example.handyman_android_app.Model;

public class GigModel {

    private double gigID;
    private String category,description,location,title;

    public GigModel(double gigID, String category, String description, String location, String title) {
        this.gigID = gigID;
        this.category = category;
        this.description = description;
        this.location = location;
        this.title = title;
    }

    public GigModel() {
    }

    public double getGigID() {
        return gigID;
    }

    public void setGigID(double gigID) {
        this.gigID = gigID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
