package com.example.astroweather.settings;

import android.os.Handler;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class ApplicationSettings {

    private AstroCalculator.Location location;
    private boolean isDayLightSaving = false;
    private int timeZoneOffset = 1;
    private AstroCalculator astroCalculator;

    final Handler handler = new Handler();
    private int updateInterval = 5000;
    private Set<SettingsUpdatedCallback> subscribers = new HashSet<>();

    private static final ApplicationSettings ourInstance = new ApplicationSettings();

    public static ApplicationSettings getInstance() {
        return ourInstance;
    }

    private ApplicationSettings() {
        location = new AstroCalculator.Location(52.229676,  21.012229);
        astroCalculator = new AstroCalculator(getAstroDateTime(), location);


        Runnable settingsUpdater = new Runnable() {
            @Override
            public void run() {
                try {
                    update();
                    notifySubscribers();
                }catch (Exception e) {
                    throw new RuntimeException();

                } finally {
                    handler.postDelayed(this, updateInterval);
                }

            }
        };
        handler.postDelayed(settingsUpdater, updateInterval);
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
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
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

}
