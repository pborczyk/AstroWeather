
package com.example.astroweather.api.pojo.weather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("long")
    @Expose
    public String _long;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("pubDate")
    @Expose
    public String pubDate;
    @SerializedName("condition")
    @Expose
    public Condition condition;
    @SerializedName("forecast")
    @Expose
    public List<Forecast> forecast = null;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("guid")
    @Expose
    public Guid guid;

}
