package com.github.weatherapp.ui.settings;

public class SettingsViewModel {
    private final String metricUnit;

    public SettingsViewModel(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public String getMetricUnit() {
        return metricUnit;
    }
}
