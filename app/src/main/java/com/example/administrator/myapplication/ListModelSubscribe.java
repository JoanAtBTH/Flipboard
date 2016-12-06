package com.example.administrator.myapplication;

import android.widget.CheckBox;
import android.widget.TextView;

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

    public void toggleSubscribed() {
        subscribed = new Boolean(!subscribed.booleanValue());
    }
}
