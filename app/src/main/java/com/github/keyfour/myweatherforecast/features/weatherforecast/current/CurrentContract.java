/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.weatherforecast.current;

import android.content.Context;
import android.location.Location;

import com.github.keyfour.myweatherforecast.model.pojo.Forecast;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */

public interface CurrentContract {

    interface View {
        void showLocation(Location location);
        void showMessage(String message);
        void showCurrentForecast(Forecast forecast);
    }

    interface Presenter {
        void start(Context context);
        void stop();
    }
}
