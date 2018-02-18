/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Description
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public class OpenWeatherMap {

    private static final String OPENWEATHER_URL = "api.openweathermap.org";

    private static OpenWeatherMap instance;
    private OpenWeatherMapService service;

    private OpenWeatherMap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPENWEATHER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class);
    }

    public static OpenWeatherMap getInstance() {
        if (instance == null) {
            instance = new OpenWeatherMap();
        }
        return instance;
    }

    public OpenWeatherMapService getService() {
        return service;
    }

}
