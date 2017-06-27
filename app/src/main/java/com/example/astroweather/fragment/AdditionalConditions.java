package com.example.astroweather.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdditionalConditions extends Fragment {

    @BindView(R.id.wind)
    public TextView wind;

    @BindView(R.id.humidity)
    public TextView humidity;

    @BindView(R.id.visibility)
    public TextView visibility;

    public AdditionalConditions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_additional_conditions, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
