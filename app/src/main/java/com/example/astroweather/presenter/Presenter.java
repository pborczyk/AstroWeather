package com.example.astroweather.presenter;

/**
 * Created by howor on 08.02.2017.
 */

public interface Presenter<V> {
    void onCreate();

    void attachView(V view);
}
