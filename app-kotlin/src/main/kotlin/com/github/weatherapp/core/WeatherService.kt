package com.github.weatherapp.core

interface WeatherService {
    fun fetchCurrentForecast(listener: Listener, city: String, unit: String)

    interface Listener {
        fun onResult(forecast: CurrentForecast)

        fun onError(throwable: Throwable)
    }
}
