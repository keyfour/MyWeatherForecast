/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.settings;

import android.content.Context;

import com.github.keyfour.myweatherforecast.data.SharedPrefUserData;
import com.github.keyfour.myweatherforecast.data.UserData;

/**
 * Description
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    private final SettingsContract.View view;

    public SettingsPresenter(SettingsContract.View view) {
        this.view = view;
    }

    @Override
    public void getApiKey(Context context) {
        UserData userData = new SharedPrefUserData(context);
        view.setInput(false);
        view.showStoredApiKey(userData.getOpenWeatherApiKey());
        view.setInput(true);
    }

    @Override
    public void storeApiKey(Context context, String key) {
        UserData userData = new SharedPrefUserData(context);
        view.setInput(false);
        userData.setOpenWeatherApiKey(key);
        view.setInput(true);
    }
}
