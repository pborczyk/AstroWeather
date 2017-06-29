package com.example.astroweather.presenter;

import com.example.astroweather.api.pojo.weather.Channel;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.fragment.BasicConditions;
import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class BasicConditionsPresenter implements Presenter<BasicConditions> {

    private BasicConditions view;
    private WeatherData weatherData;

    @Override
    public void onCreate() {
        weatherData = ApplicationSettings.getInstance().getWeatherData();

        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            view.description.setText(data.description);
            view.locationName.setText(data.location.country + " " + data.location.city);
            view.coords.setText(data.item.lat + " " + data.item._long);
            view.pressure.setText(data.atmosphere.pressure);
            view.temperature.setText(data.item.condition.temp);
            view.time.setText(data.item.pubDate);
            //image to do
        }
    }

    @Override
    public void attachView(BasicConditions view) {
        this.view = view;
    }
}
