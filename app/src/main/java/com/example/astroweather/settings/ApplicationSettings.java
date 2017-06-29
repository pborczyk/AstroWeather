package com.example.astroweather.settings;

import android.content.Context;
import android.os.Handler;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather.api.WeatherService;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.api.pojo.woeid.Woeid;
import com.example.astroweather.locations.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class ApplicationSettings {

    public static final String fav_locations_filename = "fav_locations";

    public static final String fav_location_filename = "fav_location";

    public static final String weather_filename = "weather";

    private AstroCalculator.Location location;
    private boolean isDayLightSaving = false;
    private int timeZoneOffset = 1;
    private AstroCalculator astroCalculator;

    private WeatherData weatherData;
    private Location favoriteLocation;

    final Handler handler = new Handler();
    Runnable settingsUpdater;
    private int updateInterval = 5000;
    private Set<SettingsUpdatedCallback> subscribers = new HashSet<>();

    private static final ApplicationSettings ourInstance = new ApplicationSettings();

    public static ApplicationSettings getInstance() {
        return ourInstance;
    }

    private WeatherService weatherService;

    private ApplicationSettings() {
        location = new AstroCalculator.Location(52.229676,  21.012229);
        astroCalculator = new AstroCalculator(getAstroDateTime(), location);
        weatherService = new WeatherService();

        settingsUpdater = new Runnable() {
            @Override
            public void run() {
                try {
                    update();
                    notifySubscribers();
                }catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();

                } finally {
                    handler.postDelayed(this, updateInterval);
                }

            }
        };
        handler.post(settingsUpdater);
    }

    private void notifySubscribers() {
        for (SettingsUpdatedCallback subscriber : subscribers) {
            subscriber.onSettingsUpdate();
        }
    }

    private AstroDateTime getAstroDateTime() {
        Date date = new Date();
        return new AstroDateTime(date.getYear(), date.getMonth(), date.getDay(), date.getHours(),
                date.getMinutes(), date.getSeconds(),timeZoneOffset ,isDayLightSaving);
    }

    public void setLocation(AstroCalculator.Location location) {
        this.location = location;
        handler.post(settingsUpdater);
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
        handler.post(settingsUpdater);
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public Location getFavioriteLocation() {
        return favoriteLocation;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public void setFavoriteLocation(Location favoriteLocation) {
        this.favoriteLocation = favoriteLocation;
    }

    public void update() {
        astroCalculator = new AstroCalculator(getAstroDateTime(), location);
    }

    public AstroCalculator getAstroCalculator() {
        return astroCalculator;
    }

    public void registerForUpdates(SettingsUpdatedCallback subscriber) {
        subscribers.add(subscriber);
    }

    public void unregisterForUpdates(SettingsUpdatedCallback subscriber) {
        subscribers.remove(subscriber);
    }

    public static List<Location> readLocations(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(ApplicationSettings.fav_locations_filename);
            ObjectInputStream is = new ObjectInputStream(inputStream);
            return (List<Location>) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Location readFavoriteLocation(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(ApplicationSettings.fav_location_filename);
            ObjectInputStream is = new ObjectInputStream(inputStream);
            return (Location) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WeatherData readWeatherFromFile(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(ApplicationSettings.weather_filename);
            ObjectInputStream is = new ObjectInputStream(inputStream);
            String json = (String) is.readObject();
            Gson gson = new Gson();
            return gson.fromJson(json, WeatherData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeWeatherToFile(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(ApplicationSettings.weather_filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(new Gson().toJson(getInstance().getWeatherData()));
            os.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void triggerWeatherUpdate(Context context) {
        try {
            weatherData = weatherService.getWeather(favoriteLocation.getWoeid());
            ApplicationSettings.writeWeatherToFile(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
