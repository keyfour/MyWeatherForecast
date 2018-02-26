/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.keyfour.myweatherforecast.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */

public class SettingFragment extends Fragment implements SettingsContract.View {

    private final SettingsContract.Presenter presenter;

    @BindView(R.id.et_api_key)
    EditText etApiKey;

    @BindView(R.id.bt_save)
    Button btSave;

    public SettingFragment() {
        presenter = new SettingsPresenter(this);
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_settings, group, false);
        ButterKnife.bind(this, view);
        btSave.setOnClickListener(getListener());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getApiKey(getContext());
    }

    @Override
    public void setInput(Boolean enabled) {
        etApiKey.setEnabled(enabled);
    }

    @Override
    public void showStoredApiKey(String key) {
        etApiKey.setText(key);
    }

    @NonNull
    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.storeApiKey(getContext(), etApiKey.getText().toString());
            }
        };
    }
}
