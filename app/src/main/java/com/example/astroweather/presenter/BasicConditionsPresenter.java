package com.example.astroweather.presenter;

import android.content.Context;

import com.example.astroweather.R;
import com.example.astroweather.api.pojo.weather.Channel;
import com.example.astroweather.api.pojo.weather.WeatherData;
import com.example.astroweather.fragment.BasicConditions;
import com.example.astroweather.settings.ApplicationSettings;

/**
 * Created by Piotr Borczyk on 29.06.2017.
 */

public class BasicConditionsPresenter implements Presenter<BasicConditions> {

    private BasicConditions view;
    private WeatherData weatherData;
    private Context context;

    public BasicConditionsPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        weatherData = ApplicationSettings.getInstance().getWeatherData();

        if (weatherData != null) {
            Channel data = weatherData.query.results.channel;
            view.description.setText(data.description);
            view.pressure.setText(data.atmosphere.pressure);
            view.temperature.setText(data.item.condition.temp);
            view.time.setText(data.item.pubDate);
            //image to do
            view.weatherImage.setImageDrawable(context.getResources().getDrawable(getImage(data.item.condition.code)));
        }
    }

    public int getImage(String code) {
        int codeInt = Integer.parseInt(code);
        switch (codeInt) {
            case 26:
                return R.drawable.cloudy;
            case 9:
            case 10:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
                return R.drawable.drizzle;
            case 21:
                return R.drawable.haze;
            case 28:
            case 27:
                return R.drawable.mostly_cloudy;
            case 16:
            case 13:
            case 15:
                return R.drawable.snow;
            case 36:
            case 32:
                return R.drawable.sunny;
            case 3:
            case 4:
            case 47:
            case 37:
            case 38:
            case 39:
                return R.drawable.thunderstorms;

            default:
                return R.drawable.haze;
        }
    }

    @Override
    public void attachView(BasicConditions view) {
        this.view = view;
    }
}
