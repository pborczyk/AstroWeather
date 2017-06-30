package com.example.astroweather.settings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class UnitManager {

    private ApplicationSettings settings;

    public UnitManager(ApplicationSettings settings) {
        this.settings = settings;
    }

    private TEMP_UNIT tempUnit = TEMP_UNIT.F;
    private SPEED_UNIT speedUnit = SPEED_UNIT.mph;

    public enum TEMP_UNIT {
        F,
        C
    }

    public enum SPEED_UNIT {
        mph,
        kmh
    }

    public TEMP_UNIT getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(TEMP_UNIT tempUnit) {
        this.tempUnit = tempUnit;
        settings.notifySubscribers();
    }

    public SPEED_UNIT getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(SPEED_UNIT speedUnit) {
        this.speedUnit = speedUnit;
        settings.notifySubscribers();
    }

    public String convertTemp(String input) {
        double temp = Double.parseDouble(input);
        return String.valueOf(tempUnit == TEMP_UNIT.F ? temp : (temp - 32) / 1.8);
    }

    public String convertSpeed(String input) {
        double speed = Double.parseDouble(input);
        return String.valueOf(speedUnit == SPEED_UNIT.mph ? speed : speed * 1.609);
    }
}
