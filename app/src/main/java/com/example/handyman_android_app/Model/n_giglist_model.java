package com.example.handyman_android_app.Model;

public class n_giglist_model {

    public String title ,location, category, description, gigID, userID;

    public n_giglist_model(String gigID, String userID, String category, String title, String location, String description ) {
        this.gigID = gigID;
        this.userID = userID;
        this.category = category;
        this.title = title;
        this.location = location;
        this.description = description;
    }

    public n_giglist_model() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGigID() {
        return gigID;
    }

    public void setGigID(String gigID) { this.gigID = gigID;}

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
