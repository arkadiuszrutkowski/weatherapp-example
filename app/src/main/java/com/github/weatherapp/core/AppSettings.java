package com.github.weatherapp.core;

public interface AppSettings {
    String getMetricUnit();

    void saveMetricUnit(String unit);
}
