/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.settings;

import android.content.Context;

/**
 * Description
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public interface SettingsContract {

    interface View {
        void setInput(Boolean enabled);
        void showStoredApiKey(String key);
    }

    interface Presenter {
        void getApiKey(Context context);
        void storeApiKey(Context context, String key);
    }

}
