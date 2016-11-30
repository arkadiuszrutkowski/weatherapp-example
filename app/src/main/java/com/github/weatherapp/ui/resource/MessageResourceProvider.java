package com.github.weatherapp.ui.resource;

import com.github.weatherapp.core.CurrentForecast;

public interface MessageResourceProvider {
    String getCityDescription(CurrentForecast forecast);

    String getTemperatureDescription(CurrentForecast forecast);

    String getMetricUnitDescription(String metricUnit);
}
