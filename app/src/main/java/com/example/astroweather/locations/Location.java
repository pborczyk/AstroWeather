package com.example.astroweather.locations;

import java.io.Serializable;

/**
 * Created by howor on 27.06.2017.
 */

public class Location implements Serializable {

    private String name;
    private String woeid;

    public Location(String name, String woeid) {
        this.name = name;
        this.woeid = woeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }
}
