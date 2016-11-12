package com.github.weatherapp.data;

import android.content.Context;

import com.github.weatherapp.R;
import com.github.weatherapp.core.CurrentForecast;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageResourceProvider {
    private final Context context;

    @Inject
    public MessageResourceProvider(Context context) {
        this.context = context;
    }

    public String getCityDescription(CurrentForecast forecast) {
        return context.getString(R.string.text_city_country, forecast.city, forecast.country.country);
    }

    public String getTemperatureDescription(CurrentForecast forecast) {
        return context.getString(R.string.temperature, Double.valueOf(forecast.main.temp).intValue());
    }
}
