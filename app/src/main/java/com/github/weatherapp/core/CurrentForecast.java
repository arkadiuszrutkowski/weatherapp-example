package com.github.weatherapp.core;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentForecast {
    @SerializedName("name")
    public String city;

    @SerializedName("weather")
    public List<Weather> weatherList;

    @SerializedName("main")
    public WeatherData weatherData;

    @SerializedName("sys")
    public Country country;
}
