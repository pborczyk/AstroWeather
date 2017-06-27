
package com.example.astroweather.api.pojo.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("chill")
    @Expose
    public String chill;
    @SerializedName("direction")
    @Expose
    public String direction;
    @SerializedName("speed")
    @Expose
    public String speed;

}
