
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Units {

    @SerializedName("distance")
    @Expose
    public String distance;
    @SerializedName("pressure")
    @Expose
    public String pressure;
    @SerializedName("speed")
    @Expose
    public String speed;
    @SerializedName("temperature")
    @Expose
    public String temperature;

}
