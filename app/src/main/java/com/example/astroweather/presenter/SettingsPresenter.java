package com.example.astroweather.presenter;

import android.widget.Spinner;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.SettingsActivity;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.UpdateTimeIntervalValues;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class SettingsPresenter implements Presenter<SettingsActivity> {

    private SettingsActivity view;

    @Override
    public void onCreate() {
    }

    @Override
    public void attachView(SettingsActivity view) {
        this.view = view;
    }

    public void setSettingsValues() {
        double longtitue = Double.parseDouble(view.longtitudeInput.getText().toString());
        double latitude = Double.parseDouble(view.latitudeInput.getText().toString());
        if((latitude >90 || latitude < -90)  || (longtitue < 0 || longtitue > 180) ) {
            return;
        }
        String timeInterval = (String) view.updateIntervalSpinner.getSelectedItem();
        ApplicationSettings.getInstance().setUpdateInterval(UpdateTimeIntervalValues.valueOf(timeInterval).seconds);
        ApplicationSettings.getInstance().setLocation(new AstroCalculator.Location(latitude, longtitue));
        view.finish();
    }
}
