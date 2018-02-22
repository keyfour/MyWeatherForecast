/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data.gps;

import android.content.Context;
import android.location.Location;

import io.reactivex.Observable;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */

public interface GPSService {

    Observable<Location> getLocation(Context context);

}
