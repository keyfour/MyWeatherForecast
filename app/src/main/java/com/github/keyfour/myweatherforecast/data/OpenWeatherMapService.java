/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data;

import com.github.keyfour.myweatherforecast.model.pojo.Forecast;
import com.github.keyfour.myweatherforecast.model.pojo.ForecastList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface with actions for https://openweathermap.org API
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather")
    Observable<Forecast> getForecast(@Query("lat") double lat, @Query("lon") double lon,
                                     @Query("APPID") String apiKey);

    @GET("/data/2.5/forecast")
    Observer<ForecastList> getFiveDaysForecast(@Query("lat") double lat, @Query("lon") double lon,
                                               @Query("APPID") String apiKey);


}
