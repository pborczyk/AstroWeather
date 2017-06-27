package com.example.astroweather.api;

import com.example.astroweather.api.pojo.woeid.Woeid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by howor on 27.06.2017.
 */

public class Test implements Callback<Woeid> {
    public static void main(String[] args) {
        Test test = new Test();
        test.start();
    }

    static final String BASE_URL = "https://query.yahooapis.com";


    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        YahooWeather yahooWeather = retrofit.create(YahooWeather.class);

        //Call<WeatherData> call = yahooWeather.queryForWeather("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"nome, ak\")","json", "store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        Call<Woeid> call = yahooWeather.queryForWoid("select woeid from geo.places(1) where text=\"nome, ak\"", "json","store%3A%2F%2Fdatatables.org%2Falltableswithkeys" );
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<Woeid> call, Response<Woeid> response) {
        if(response.isSuccessful()) {
            Woeid changesList = response.body();
            System.out.printf("ddda");
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Woeid> call, Throwable t) {
        t.printStackTrace();
    }
}
