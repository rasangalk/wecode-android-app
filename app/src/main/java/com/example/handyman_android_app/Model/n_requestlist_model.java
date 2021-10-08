package com.example.handyman_android_app.Model;

public class n_requestlist_model {

    public String description ,location, date, gigID, requestID;


    public n_requestlist_model(String gigID, String date, String description, String location, String requestID) {
        this.gigID = gigID;
        this.date = date;
        this.description = description;
        this.location = location;
        this.requestID = requestID;
    }

    public n_requestlist_model() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGigID() {
        return gigID;
    }

    public void setGigID(String gigID) {
        this.gigID = gigID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
