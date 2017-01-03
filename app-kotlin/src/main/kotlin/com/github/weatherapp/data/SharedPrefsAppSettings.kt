package com.github.weatherapp.data

import android.content.SharedPreferences

import com.github.weatherapp.core.AppSettings

class SharedPrefsAppSettings(private val preferences: SharedPreferences) : AppSettings {

    override var metricUnit: String
        get() = preferences.getString(KEY_METRIC, "metric")
        set(unit) = preferences.edit().putString(KEY_METRIC, unit).apply()

    companion object {
        private val KEY_METRIC = "PREFERENCE_METRIC"
    }
}
