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

    }

    @Override
    public void attachView(ForecastViewHolder view) {
        this.view = view;
    }
}
