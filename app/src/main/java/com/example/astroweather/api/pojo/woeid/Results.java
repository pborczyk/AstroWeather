
package com.example.astroweather.api.pojo.woeid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("place")
    @Expose
    public Place place;

}
