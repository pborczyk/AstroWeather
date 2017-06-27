
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("day")
    @Expose
    public String day;
    @SerializedName("high")
    @Expose
    public String high;
    @SerializedName("low")
    @Expose
    public String low;
    @SerializedName("text")
    @Expose
    public String text;

}
