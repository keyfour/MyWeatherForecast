/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.weatherforecast.fivedays;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.keyfour.myweatherforecast.R;
import com.github.keyfour.myweatherforecast.model.pojo.ForecastList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */

public class FivedaysFragment extends Fragment implements FivedaysContract.View {

    private final FivedaysContract.Presenter presenter;
    private Context context;

    @BindView(R.id.tv_latitude)
    TextView tvLatitude;

    @BindView(R.id.tv_longtitude)
    TextView tvLongtitude;

    @BindView(R.id.rv_forecast)
    RecyclerView rvForecast;

    public FivedaysFragment() {
        presenter = new FivedaysPresenter(this);
    }

    public static FivedaysFragment newInstance() {
        return new FivedaysFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_wf_fivedays, group, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start(getContext());
        context = getContext();
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
    public void showFivedaysForecast(ForecastList forecastList) {
        if (forecastList != null && context != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            RecyclerView.Adapter adapter = new FivedaysAdapter(forecastList);
            rvForecast.setLayoutManager(layoutManager);
            rvForecast.setAdapter(adapter);
        }
    }
}
