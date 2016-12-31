package com.github.weatherapp.data

import android.content.SharedPreferences

import com.github.weatherapp.core.AppSettings

class SharedPrefsAppSettings(private val preferences: SharedPreferences) : AppSettings {

    override val metricUnit: String
        get() = preferences.getString(KEY_METRIC, "metric")

    override fun saveMetricUnit(unit: String) {
        preferences.edit()
                .putString(KEY_METRIC, unit)
                .apply()
    }

    companion object {
        private val KEY_METRIC = "PREFERENCE_METRIC"
    }
}
