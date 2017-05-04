package com.example.astroweather.presenter;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.fragment.MoonFragment;
import com.example.astroweather.fragment.SunFragment;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class SunPresenter implements Presenter<SunFragment>, SettingsUpdatedCallback {

    private ApplicationSettings applicationSettings;
    private SunFragment view;
    private AstroCalculator astroCalculator;
    private AstroCalculator.SunInfo sunInfo;

    @Override
    public void onCreate() {
        applicationSettings = ApplicationSettings.getInstance();
        updateView();
        applicationSettings.registerForUpdates(this);
    }

    private void updateView() {
        astroCalculator = applicationSettings.getAstroCalculator();
        sunInfo = astroCalculator.getSunInfo();

        view.sunriseTime.setText(sunInfo.getSunrise().toString());
        view.azimuthSunrise.setText(String.valueOf(sunInfo.getAzimuthRise()));
        view.sunSetTime.setText(sunInfo.getSunset().toString());
        view.azimuthSunset.setText(String.valueOf(sunInfo.getAzimuthSet()));
        view.civilSunriseTime.setText(sunInfo.getTwilightMorning().toString());
        view.civilSunsetTime.setText(sunInfo.getTwilightEvening().toString());
    }

    @Override
    public void attachView(SunFragment view) {
        this.view = view;
    }

    @Override
    public void onSettingsUpdate() {
        updateView();
    }

    public void onDestroy() {
        applicationSettings.unregisterForUpdates(this);
    }
}
