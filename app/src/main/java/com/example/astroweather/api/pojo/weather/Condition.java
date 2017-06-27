
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("temp")
    @Expose
    public String temp;
    @SerializedName("text")
    @Expose
    public String text;

}
