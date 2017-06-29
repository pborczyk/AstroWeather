package com.example.astroweather.presenter;

import com.example.astroweather.api.pojo.weather.Channel;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.fragment.AdditionalConditions;
import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class AdditionalConditionsPresenter implements Presenter<AdditionalConditions> {

    private AdditionalConditions view;

    @Override
    public void onCreate() {
        WeatherData weatherData = ApplicationSettings.getInstance().getWeatherData();

        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            view.humidity.setText(data.atmosphere.humidity);
            view.visibility.setText(data.atmosphere.visibility);
            view.wind.setText(data.wind.speed);
        }
    }

    @Override
    public void attachView(AdditionalConditions view) {
        this.view = view;
    }
}
