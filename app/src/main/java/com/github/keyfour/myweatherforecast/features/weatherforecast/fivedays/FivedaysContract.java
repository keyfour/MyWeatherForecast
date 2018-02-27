/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.weatherforecast.fivedays;

import android.content.Context;
import android.location.Location;

import com.github.keyfour.myweatherforecast.model.pojo.ForecastList;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */

public interface FivedaysContract {

    interface View {
        void showLocation(Location location);
        void showMessage(String message);
        void showFivedaysForecast(ForecastList forecastList);
    }

    interface Presenter {
        void start(Context context);
        void stop();
    }
}
