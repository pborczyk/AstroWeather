package com.example.astroweather.presenter;

import com.example.astroweather.adapter.ForecastViewHolder;
import com.example.astroweather.api.pojo.weather.Forecast;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class ForecastTilePresenter implements Presenter<ForecastViewHolder> {

    private ForecastViewHolder view;
    private Forecast forecast;

    public ForecastTilePresenter(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public void onCreate() {
        view.day.setText(forecast.day);
        view.description.setText(forecast.text);
        view.maxTemp.setText(forecast.high);
        view.minTemp.setText(forecast.low);
    }

    @Override
    public void attachView(ForecastViewHolder view) {
        this.view = view;
    }
}
