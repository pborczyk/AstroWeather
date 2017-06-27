
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("width")
    @Expose
    public String width;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("url")
    @Expose
    public String url;

}
