package com.example.astroweather.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.astroweather.R;

import java.util.List;

/**
 * Created by howor on 27.06.2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {


    private List<Location> data;

    public LocationAdapter(List<Location> data) {
        this.data = data;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_custom_row, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        Location location = data.get(position);
        holder.locationName.setText(location.getName());
        holder.woeid.setText(location.getWoeid());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
