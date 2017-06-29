package com.example.astroweather.presenter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.R;
import com.example.astroweather.SettingsActivity;
import com.example.astroweather.adapter.LocationSpinnerAdapter;
import com.example.astroweather.api.WeatherService;
import com.example.astroweather.locations.Location;
import com.example.astroweather.locations.LocationAdapter;
import com.example.astroweather.settings.ApplicationSettings;
import com.example.astroweather.settings.UpdateTimeIntervalValues;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class SettingsPresenter implements Presenter<SettingsActivity> {

    private SettingsActivity view;
    private Context context;
    private WeatherService weatherService;

    public SettingsPresenter(Context context) {
        this.context = context;
        weatherService = new WeatherService();
    }

    @Override
    public void onCreate() {
        populateFavoritesSpinner();
    }

    private void populateFavoritesSpinner() {
        List<Location> favoriteLocations = ApplicationSettings.readLocations(context);
        LocationSpinnerAdapter arrayAdapter = new LocationSpinnerAdapter(context, R.layout.spinner_text, favoriteLocations);
        view.favoritesSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void attachView(SettingsActivity view) {
        this.view = view;
    }

    public void setSettingsValues() {
//        double longtitue = Double.parseDouble(view.longtitudeInput.getText().toString());
//        double latitude = Double.parseDouble(view.latitudeInput.getText().toString());
//        if((latitude >90 || latitude < -90)  || (longtitue < 0 || longtitue > 180) ) {
//            return;
//        }
//        String timeInterval = (String) view.updateIntervalSpinner.getSelectedItem();
//        ApplicationSettings.getInstance().setUpdateInterval(UpdateTimeIntervalValues.valueOf(timeInterval).seconds);
//        ApplicationSettings.getInstance().setLocation(new AstroCalculator.Location(latitude, longtitue));
//        view.finish();
    }

    public void onSaveFavorites() {
        Location location = (Location) view.favoritesSpinner.getSelectedItem();
        ApplicationSettings.getInstance().setFavoriteLocation(location);
        ApplicationSettings.getInstance().triggerWeatherUpdate(context);
    }

    public void saveLocationFromInput() {
        String location = view.locationInput.getText().toString();
        String woeid = weatherService.getVoid(location);
        if (woeid != null) {
            Location newLocation = new Location(location, woeid);
            ApplicationSettings.getInstance().setFavoriteLocation(newLocation);
            ApplicationSettings.getInstance().triggerWeatherUpdate(context);
        } else {
            //ERROR
        }
    }

    protected void onDestroy() {
        try {
            FileOutputStream outputStream = context.openFileOutput(ApplicationSettings.fav_location_filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(ApplicationSettings.getInstance().getFavioriteLocation());
            os.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
