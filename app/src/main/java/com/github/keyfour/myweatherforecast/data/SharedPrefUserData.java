/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.data;

import android.content.Context;

/**
 * Description
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public class SharedPrefUserData implements UserData {

    private final Context context;
    private static final String FILE = "UDATA";
    private static final String KEY = "OWAKEY";

    public SharedPrefUserData(Context context) {
        this.context = context;
    }

    @Override
    public void setOpenWeatherApiKey(String key) {
        if (context != null) {
            context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
                    .edit().putString(KEY, key).apply();
        }
    }

    @Override
    public String getOpenWeatherApiKey() {
        if (context != null) {
            return context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
                    .getString(KEY,"");
        }
        return null;
    }
}
