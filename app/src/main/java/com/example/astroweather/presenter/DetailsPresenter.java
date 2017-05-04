package com.example.astroweather.presenter;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.fragment.DetailsFragment;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class DetailsPresenter implements Presenter<DetailsFragment>, SettingsUpdatedCallback {

    private DetailsFragment view;
    private ApplicationSettings applicationSettings;


    @Override
    public void onCreate() {
        applicationSettings = ApplicationSettings.getInstance();
        updateView();
        applicationSettings.registerForUpdates(this);
    }

    @Override
    public void attachView(DetailsFragment view) {
        this.view = view;
    }

    @Override
    public void onSettingsUpdate() {
        updateView();
    }

    private void updateView() {
        AstroCalculator astroCalculator = applicationSettings.getAstroCalculator();
        view.currentLatitude.setText(String.valueOf(astroCalculator.getLocation().getLatitude()));
        view.currentLongtitude.setText(String.valueOf(astroCalculator.getLocation().getLongitude()));
    }

    public void onDestroy() {
        applicationSettings.unregisterForUpdates(this);
    }
}
