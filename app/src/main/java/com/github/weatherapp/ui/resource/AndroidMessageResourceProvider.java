package com.github.weatherapp.ui.resource;

import android.content.Context;
import android.support.annotation.ArrayRes;

import com.github.weatherapp.R;
import com.github.weatherapp.core.CurrentForecast;

import java.util.Arrays;

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
        return context.getString(R.string.temperature, Double.valueOf(forecast.weatherData.temp).intValue());
    }

    @Override
    public String getMetricUnitDescription(String metricUnit) {
        return getDescriptionStringFromValue(
                metricUnit, R.array.temperature_unit_values, R.array.temperature_unit_descriptions);
    }

    private String getDescriptionStringFromValue(String value, @ArrayRes int valueArrayResId, @ArrayRes int descArrayResId) {
        String[] unitValues = context.getResources().getStringArray(valueArrayResId);
        String[] units = context.getResources().getStringArray(descArrayResId);

        int unitIdx = Arrays.asList(unitValues).indexOf(value);

        return units[unitIdx];
    }
}
