package com.example.astroweather.api;

import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.api.pojo.woeid.Woeid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by howor on 27.06.2017.
 */

public interface YahooWeather {

    @GET("/v1/public/yql")
    Call<WeatherData> queryForWeather(@Query("q") String query,
                                      @Query("format") String format,
                                      @Query("env") String enviromment);

    @GET("/v1/public/yql")
    Call<Woeid> queryForWoid(@Query("q") String query,
                             @Query("format") String format,
                             @Query("env") String enviromment);

}
