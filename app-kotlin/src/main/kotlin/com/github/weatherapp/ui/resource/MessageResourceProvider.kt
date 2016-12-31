package com.github.weatherapp.ui.resource

import com.github.weatherapp.core.CurrentForecast

interface MessageResourceProvider {
    fun getCityDescription(forecast: CurrentForecast): String

    fun getTemperatureDescription(forecast: CurrentForecast): String

    fun getMetricUnitDescription(metricUnit: String): String
}
