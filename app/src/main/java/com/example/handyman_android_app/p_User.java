package com.example.handyman_android_app;

public class p_User {
    String customer, title ,location;


    public p_User(){}

    public p_User(String customer, String title, String location) {
        this.customer = customer;
        this.title = title;
        this.location = location;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
