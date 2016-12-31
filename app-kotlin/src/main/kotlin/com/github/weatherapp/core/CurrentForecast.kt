package com.github.weatherapp.core

import com.google.gson.annotations.SerializedName

class CurrentForecast {
    @SerializedName("name")
    var city: String? = null

    @SerializedName("weather")
    var weatherList: List<Weather>? = null

    @SerializedName("main")
    var weatherData: WeatherData? = null

    @SerializedName("sys")
    var country: Country? = null
}
