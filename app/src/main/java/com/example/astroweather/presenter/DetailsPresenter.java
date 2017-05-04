package com.example.astroweather.presenter;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.fragment.DetailsFragment;
import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class DetailsPresenter implements Presenter<DetailsFragment> {

    private DetailsFragment view;

    @Override
    public void onCreate() {
        ApplicationSettings applicationSettings = ApplicationSettings.getInstance();
        AstroCalculator astroCalculator = applicationSettings.getAstroCalculator();
        view.currentLocation.setText(astroCalculator.getLocation().toString());
        view.currentTime.setText(astroCalculator.getDateTime().toString());
    }

    @Override
    public void attachView(DetailsFragment view) {
        this.view = view;
    }
}
