package com.example.astroweather.locations;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.astroweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by howor on 27.06.2017.
 */

public class LocationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.location_name)
    public TextView locationName;

    @BindView(R.id.woeid)
    public TextView woeid;

    public LocationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
