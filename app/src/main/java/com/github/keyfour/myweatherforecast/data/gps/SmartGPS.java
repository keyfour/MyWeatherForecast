/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data.gps;

import android.content.Context;
import android.location.Location;

import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.rx.ObservableFactory;
import io.reactivex.Observable;

/**
 *
 * Simple class which use Smart Location Library
 * (https://github.com/mrmans0n/smart-location-lib)
 * to handle location changes
 *
 * @author Alex Karpov <keyfour13@gmail.com>
 *
 */

public class SmartGPS implements GPSService {

    @Override
    public Observable<Location> getLocation(Context context) {
        return ObservableFactory.from(SmartLocation.with(context).location());
    }

}
