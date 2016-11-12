package com.github.weatherapp.ui.weather;

public class CurrentWeatherViewModel {
    private final String temperature;
    private final String cityName;
    private final String weatherIconPath;

    public CurrentWeatherViewModel(String temperature, String cityName, String weatherIconPath) {
        this.temperature = temperature;
        this.cityName = cityName;
        this.weatherIconPath = weatherIconPath;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherIconPath() {
        return weatherIconPath;
    }
}
