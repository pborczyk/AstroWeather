
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("query")
    @Expose
    public Query query;

}
