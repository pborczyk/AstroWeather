package com.example.astroweather.presenter;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.fragment.MoonFragment;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.SettingsUpdatedCallback;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class MoonPresenter implements Presenter<MoonFragment>, SettingsUpdatedCallback {

    private ApplicationSettings applicationSettings;
    private MoonFragment view;
    private AstroCalculator astroCalculator;
    private AstroCalculator.MoonInfo moonInfo;

    @Override
    public void onCreate() {
        applicationSettings = ApplicationSettings.getInstance();
        updateView();
        applicationSettings.registerForUpdates(this);
    }

    private void updateView() {
        applicationSettings = ApplicationSettings.getInstance();
        astroCalculator = applicationSettings.getAstroCalculator();
        moonInfo = astroCalculator.getMoonInfo();

        view.moonPhase.setText(String.valueOf(moonInfo.getIllumination()));
        view.moonRiseTime.setText(moonInfo.getMoonrise().toString());
        view.moonSetTime.setText(moonInfo.getMoonset().toString());
        view.nearestFullMoon.setText(moonInfo.getNextFullMoon().toString());
        view.nearestNewMoon.setText(moonInfo.getNextNewMoon().toString());
        view.synodicMonthDay.setText(String.valueOf(moonInfo.getAge()));
    }

    @Override
    public void attachView(MoonFragment view) {
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
