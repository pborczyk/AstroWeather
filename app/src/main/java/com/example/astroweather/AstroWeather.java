package com.example.astroweather;

import android.app.Application;

import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class AstroWeather extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationSettings.getInstance().setFavoriteLocation(ApplicationSettings.readFavoriteLocation(getApplicationContext()));
        ApplicationSettings.getInstance().setWeatherData(ApplicationSettings.readWeatherFromFile(getApplicationContext()));
    }
}
