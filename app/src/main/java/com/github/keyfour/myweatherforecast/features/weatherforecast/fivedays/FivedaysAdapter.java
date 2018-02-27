/*
 * Copyright 2018 (c) Aleksandr Karpov <keyfour13@gmail.com>
 */
package com.github.keyfour.myweatherforecast.features.weatherforecast.fivedays;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.keyfour.myweatherforecast.R;
import com.github.keyfour.myweatherforecast.model.pojo.ForecastList;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Alex Karpov <keyfour13@gmail.com>
 */

public class FivedaysAdapter extends RecyclerView.Adapter<FivedaysAdapter.ViewHolder> {

    private final ForecastList forecastList;

    public FivedaysAdapter(ForecastList forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public FivedaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wf_fivedays,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (forecastList.getList() != null) {
            holder.tvTemp.setText(String.format(Locale.getDefault(),
                    "%f", forecastList.getList().get(position).getMain().getTemp()));
            holder.tvHum.setText(String.format(Locale.getDefault(),
                    "%d", forecastList.getList().get(position).getMain().getHumidity()));
            holder.tvPress.setText(String.format(Locale.getDefault(),
                    "%d", forecastList.getList().get(position).getMain().getPressure()));
        }
    }

    @Override
    public int getItemCount() {
        if (forecastList.getList() != null) {
            return forecastList.getList().size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_temp)
        public TextView tvTemp;
        @BindView(R.id.tv_hum)
        public TextView tvHum;
        @BindView(R.id.tv_press)
        public TextView tvPress;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
