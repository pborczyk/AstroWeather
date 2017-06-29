package com.example.astroweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.astroweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.min_temp)
    public TextView minTemp;

    @BindView(R.id.max_temp)
    public TextView maxTemp;

    @BindView(R.id.description)
    public TextView description;

    @BindView(R.id.day)
    public TextView day;

    public ForecastViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
