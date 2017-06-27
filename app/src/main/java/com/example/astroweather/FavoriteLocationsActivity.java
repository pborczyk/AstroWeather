package com.example.astroweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteLocationsActivity extends AppCompatActivity {

    @BindView(R.id.places_list)
    public RecyclerView placesList;

    @BindView(R.id.fab)
    public FloatingActionButton addPlaceButton;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_locations);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

}
