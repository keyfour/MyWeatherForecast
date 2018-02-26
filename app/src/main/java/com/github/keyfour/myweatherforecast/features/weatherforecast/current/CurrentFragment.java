/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.weatherforecast.current;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.keyfour.myweatherforecast.R;
import com.github.keyfour.myweatherforecast.model.pojo.Forecast;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */
public class CurrentFragment extends Fragment implements CurrentContract.View {

    private final CurrentContract.Presenter presenter;

    @BindView(R.id.tv_latitude)
    TextView tvLatitude;

    @BindView(R.id.tv_longtitude)
    TextView tvLongtitude;

    @BindView(R.id.tv_temp)
    TextView tvTemp;

    @BindView(R.id.tv_hum)
    TextView tvHum;

    @BindView(R.id.tv_press)
    TextView tvPress;

    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;

    public CurrentFragment() {
        presenter = new CurrentPresenter(this);
    }

    public static CurrentFragment newInstance() {
        return new CurrentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_wf_current, group, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void showLocation(Location location) {
        if (location != null) {
            tvLatitude.setText(String.valueOf(location.getLatitude()));
            tvLongtitude.setText(String.valueOf(location.getLongitude()));
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCurrentForecast(Forecast forecast) {
        if (forecast != null) {
            tvLatitude.setText(String.format(Locale.getDefault(), "%f",
                    forecast.getCoord().getLat()));
            tvLongtitude.setText(String.format(Locale.getDefault(), "%f",
                    forecast.getCoord().getLon()));
            tvTemp.setText(String.format(Locale.getDefault(),"%f",
                    forecast.getMain().getTemp()));
            tvHum.setText(String.format(Locale.getDefault(), "%d",
                    forecast.getMain().getHumidity()));
            tvPress.setText(String.format(Locale.getDefault(), "%d",
                    forecast.getMain().getPressure()));
            Picasso.with(getContext())
                    .load("http://openweathermap.org/img/w/"
                            + forecast.getWeather().get(0).getIcon() + ".png").into(ivWeatherIcon);
        }
    }
}
