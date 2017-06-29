package com.example.astroweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.astroweather.R;
import com.example.astroweather.api.pojo.weather.Forecast;
import com.example.astroweather.presenter.ForecastPresenter;
import com.example.astroweather.presenter.ForecastTilePresenter;

import java.util.List;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private List<Forecast> data;

    public ForecastAdapter(List<Forecast> data) {
        this.data = data;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_tile, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecast forecast = data.get(position);
        ForecastTilePresenter presenter = new ForecastTilePresenter(forecast);
        presenter.attachView(holder);
        presenter.onCreate();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
