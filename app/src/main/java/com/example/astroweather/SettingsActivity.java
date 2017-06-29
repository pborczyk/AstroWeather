package com.example.astroweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.astroweather.presenter.SettingsPresenter;
import com.example.astroweather.settings.UpdateTimeIntervalValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.update_interval_spinner)
    public Spinner updateIntervalSpinner;

    @BindView(R.id.save_interval_button)
    public Button saveIntervalButton;

    @BindView(R.id.favorites_spinner)
    public Spinner favoritesSpinner;

    @BindView(R.id.save_favorite_button)
    public Button saveFavoritesButton;

    @BindView(R.id.location_input)
    public EditText locationInput;

    @BindView(R.id.save_location_button)
    public Button saveLocationButton;

    @BindView(R.id.update_button)
    public Button updateButton;

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        List<String> intervalNames = new ArrayList<>();
        for (UpdateTimeIntervalValues value : UpdateTimeIntervalValues.values()) {
            intervalNames.add(value.name());
        }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interval_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateIntervalSpinner.setAdapter(adapter);

        presenter = new SettingsPresenter(this);
        presenter.attachView(this);
        presenter.onCreate();

        saveIntervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setSettingsValues();
            }
        });

        saveFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveFavorites();
            }
        });

        saveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveLocationFromInput();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateWeather();
            }
        });
    }
}
