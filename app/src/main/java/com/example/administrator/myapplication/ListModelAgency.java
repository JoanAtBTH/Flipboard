package com.example.administrator.myapplication;

/**
 * Created by joancolom on 15/11/16.
 */

public class ListModelAgency {
    // private variables
    private String name;
    private Boolean subscribed;

    public ListModelAgency(String name, Boolean subscribed) {
        this.name = name;
        this.subscribed = subscribed;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }
}
