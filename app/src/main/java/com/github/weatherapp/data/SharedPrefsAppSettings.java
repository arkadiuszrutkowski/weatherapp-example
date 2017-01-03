package com.github.weatherapp.data;

import android.content.SharedPreferences;

import com.github.weatherapp.core.AppSettings;

public class SharedPrefsAppSettings implements AppSettings {
    private static final String KEY_METRIC = "PREFERENCE_METRIC";
    private final SharedPreferences preferences;

    public SharedPrefsAppSettings(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public String getMetricUnit() {
        return preferences.getString(KEY_METRIC, "metric");
    }

    @Override
    public void setMetricUnit(String unit) {
        preferences.edit()
                .putString(KEY_METRIC, unit)
                .apply();
    }
}
