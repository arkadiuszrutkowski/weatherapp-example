package com.github.weatherapp.ui.resource;

import android.content.Context;

import com.github.weatherapp.R;
import com.github.weatherapp.core.CurrentForecast;

public class AndroidMessageResourceProvider implements MessageResourceProvider {
    private final Context context;

    public AndroidMessageResourceProvider(Context context) {
        this.context = context;
    }

    @Override
    public String getCityDescription(CurrentForecast forecast) {
        return context.getString(R.string.text_city_country, forecast.city, forecast.country.country);
    }

    @Override
    public String getTemperatureDescription(CurrentForecast forecast) {
        return context.getString(R.string.temperature, Double.valueOf(forecast.main.temp).intValue());
    }
}
