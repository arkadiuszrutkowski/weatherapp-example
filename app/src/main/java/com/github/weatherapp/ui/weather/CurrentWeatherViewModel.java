package com.github.weatherapp.ui.weather;

class CurrentWeatherViewModel {
    private final String temperature;
    private final String cityName;
    private final String weatherIconPath;

    CurrentWeatherViewModel(String temperature, String cityName, String weatherIconPath) {
        this.temperature = temperature;
        this.cityName = cityName;
        this.weatherIconPath = weatherIconPath;
    }

    String getTemperature() {
        return temperature;
    }

    String getCityName() {
        return cityName;
    }

    String getWeatherIconPath() {
        return weatherIconPath;
    }
}
