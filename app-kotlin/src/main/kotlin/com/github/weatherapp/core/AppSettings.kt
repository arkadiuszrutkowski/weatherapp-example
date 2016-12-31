package com.github.weatherapp.core

interface AppSettings {
    val metricUnit: String

    fun saveMetricUnit(unit: String)
}
