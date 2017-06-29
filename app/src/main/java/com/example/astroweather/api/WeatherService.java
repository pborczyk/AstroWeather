package com.example.astroweather.api;

import android.os.AsyncTask;

import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.api.pojo.woeid.Woeid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by howor on 27.06.2017.
 */

public class WeatherService {

    private static final String BASE_URL = "https://query.yahooapis.com";

    public String getVoid(String location) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                YahooWeather yahooWeather = retrofit.create(YahooWeather.class);

                Call<Woeid> call = yahooWeather.queryForWoid("select woeid from geo.places(1) where text=\"" + params[0] + "\"", "json","store%3A%2F%2Fdatatables.org%2Falltableswithkeys" );
                try {
                    Response<Woeid> response = call.execute();
                    if (response.isSuccessful() && response.body().query.results != null) {
                        return response.body().query.results.place.woeid;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        try {
            return task.execute(location).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeatherData getWeather(String woeid) throws IOException {
        AsyncTask<String, Void, WeatherData> task = new AsyncTask<String, Void, WeatherData>() {

            Throwable exception = null;

            @Override
            protected WeatherData doInBackground(String... params) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                YahooWeather yahooWeather = retrofit.create(YahooWeather.class);

                Call<WeatherData> call = yahooWeather.queryForWeather("select * from weather.forecast where woeid = " + params[0], "json", "store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                try {
                    Response<WeatherData> response = call.execute();
                    if (response.isSuccessful()) {
                        return response.body();
                    }
                } catch (IOException e) {
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(WeatherData s) {
                super.onPostExecute(s);
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
        };
        try {
            return task.execute(woeid).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

