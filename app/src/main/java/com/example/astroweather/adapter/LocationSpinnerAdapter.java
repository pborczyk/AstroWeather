package com.example.astroweather.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.locations.Location;


import java.util.List;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class LocationSpinnerAdapter extends ArrayAdapter<Location> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public LocationSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, List<Location> locations) {
        super(context, resource, locations);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.spinner_text, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.spinner_text_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Location item = getItem(position);
        if (item != null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(String.format("%s", item.getName()));
        }

        return convertView;
    }


}
