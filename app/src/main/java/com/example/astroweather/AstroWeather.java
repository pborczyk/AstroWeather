package com.example.astroweather;

import android.app.Application;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class AstroWeather extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationSettings instance = ApplicationSettings.getInstance();
        instance.setFavoriteLocation(ApplicationSettings.readFavoriteLocation(getApplicationContext()));
        instance.setWeatherData(ApplicationSettings.readWeatherFromFile(getApplicationContext()));
        if (instance.getWeatherData() != null) {
            double longtitude = Double.parseDouble(instance.getWeatherData().query.results.channel.item._long);
            double latitude = Double.parseDouble(instance.getWeatherData().query.results.channel.item.lat);
            instance.setLocation(new AstroCalculator.Location(latitude, longtitude));

        }
    }
}
