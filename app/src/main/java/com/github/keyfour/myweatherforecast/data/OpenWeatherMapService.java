/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data;

import com.github.keyfour.myweatherforecast.model.pojo.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface with actions for https://openweathermap.org API
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather")
    Observable<Forecast> getForecast(@Query("lat") Float lat, @Query("lon") Float lon);


}
