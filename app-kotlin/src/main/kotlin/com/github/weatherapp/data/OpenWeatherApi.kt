package com.github.weatherapp.data

import com.github.weatherapp.core.CurrentForecast

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeatherForecast(@Query("appid") apiKey: String, @Query("q") city: String, @Query("units") unit: String): Call<CurrentForecast>
}
