package com.github.weatherapp.data;

import com.github.weatherapp.core.CurrentForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("weather")
    Call<CurrentForecast> getCurrentWeatherForecast(@Query("appid") String apiKey, @Query("q") String city, @Query("units") String unit);
}
