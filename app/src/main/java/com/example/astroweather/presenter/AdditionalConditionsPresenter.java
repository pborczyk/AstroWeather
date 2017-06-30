package com.example.astroweather.presenter;

import com.example.astroweather.R;
import com.example.astroweather.api.pojo.weather.Channel;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.fragment.AdditionalConditions;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class AdditionalConditionsPresenter implements Presenter<AdditionalConditions>, SettingsUpdatedCallback {

    private AdditionalConditions view;

    @Override
    public void onCreate() {
        ApplicationSettings settings = ApplicationSettings.getInstance();
        WeatherData weatherData = settings.getWeatherData();
        settings.registerForUpdates(this);

        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            view.humidity.setText(data.atmosphere.humidity);
            view.visibility.setText(data.atmosphere.visibility);
            view.wind.setText(settings.getUnitManager().convertSpeed(data.wind.speed));
        }
    }

    @Override
    public void attachView(AdditionalConditions view) {
        this.view = view;
    }

    public void onDestroy() {
        ApplicationSettings.getInstance().unregisterForUpdates(this);
    }

    @Override
    public void onSettingsUpdate() {
        ApplicationSettings settings = ApplicationSettings.getInstance();
        WeatherData weatherData = settings.getWeatherData();

        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            view.humidity.setText(data.atmosphere.humidity);
            view.visibility.setText(data.atmosphere.visibility);
            view.wind.setText(settings.getUnitManager().convertSpeed(data.wind.speed));
        }
    }
}
