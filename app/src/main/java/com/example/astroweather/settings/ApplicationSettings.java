package com.example.astroweather.settings;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class ApplicationSettings {

    private AstroCalculator.Location location;
    private boolean isDayLightSaving = false;
    private int timeZoneOffset = 1;
    private AstroCalculator astroCalculator;

    private static final ApplicationSettings ourInstance = new ApplicationSettings();

    public static ApplicationSettings getInstance() {
        return ourInstance;
    }

    private ApplicationSettings() {
        location = new AstroCalculator.Location(52.229676,  21.012229);
        astroCalculator = new AstroCalculator(getAstroDateTime(), location);
    }

    private AstroDateTime getAstroDateTime() {
        Date date = new Date();
        return new AstroDateTime(date.getYear(), date.getMonth(), date.getDay(), date.getHours(),
                date.getMinutes(), date.getSeconds(),timeZoneOffset ,isDayLightSaving);
    }

    public void setLocation(AstroCalculator.Location location) {
        this.location = location;
    }

    public void update() {
        astroCalculator = new AstroCalculator(getAstroDateTime(), location);
    }

    public AstroCalculator getAstroCalculator() {
        return astroCalculator;
    }
}