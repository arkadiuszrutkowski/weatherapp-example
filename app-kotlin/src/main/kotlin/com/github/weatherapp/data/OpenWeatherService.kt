package com.github.weatherapp.data

import com.github.weatherapp.core.CurrentForecast
import com.github.weatherapp.core.WeatherService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenWeatherService(private val api: OpenWeatherApi, private val apiKey: String) : WeatherService {

    override fun fetchCurrentForecast(listener: WeatherService.Listener, city: String, unit: String) {
        api.getCurrentWeatherForecast(apiKey, city, unit).enqueue(object : Callback<CurrentForecast> {
            override fun onResponse(call: Call<CurrentForecast>, response: Response<CurrentForecast>) {
                listener.onResult(response.body())
            }

            override fun onFailure(call: Call<CurrentForecast>, t: Throwable) {
                listener.onError(t)
            }
        })
    }
}
