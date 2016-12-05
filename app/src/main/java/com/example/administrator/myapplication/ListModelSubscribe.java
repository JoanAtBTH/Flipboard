package com.example.administrator.myapplication;

/**
 * Created by joancolom on 15/11/16.
 */

public class ListModelSubscribe {
    // private variables
    private String name;
    private Boolean subscribed;

    public ListModelSubscribe(String name, Boolean subscribed) {
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

    public boolean isSubscribed() {
        return subscribed.booleanValue();
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }
}
