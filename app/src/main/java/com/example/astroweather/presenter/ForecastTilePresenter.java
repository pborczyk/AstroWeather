package com.example.astroweather.presenter;

import com.example.astroweather.R;
import com.example.astroweather.adapter.ForecastViewHolder;
import com.example.astroweather.api.pojo.weather.Forecast;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class ForecastTilePresenter implements Presenter<ForecastViewHolder> {

    private ForecastViewHolder view;
    private Forecast forecast;
    private ApplicationSettings settings = ApplicationSettings.getInstance();

    public ForecastTilePresenter(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public void onCreate() {
        setLabels();
    }

    private void setLabels() {
        view.day.setText(forecast.day);
        view.description.setText(forecast.text);
        view.maxTemp.setText(settings.getUnitManager().convertTemp(forecast.high));
        view.minTemp.setText(settings.getUnitManager().convertTemp(forecast.low));
    }

    @Override
    public void attachView(ForecastViewHolder view) {
        this.view = view;
    }
}
