package com.example.handyman_android_app;

public class p_User {
    String title ,location, category;


    public p_User(){}

    public p_User(String category, String title, String location) {
        this.category = category;
        this.title = title;
        this.location = location;
    }

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
