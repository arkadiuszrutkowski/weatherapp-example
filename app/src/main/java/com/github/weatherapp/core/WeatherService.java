package com.github.weatherapp.core;

public interface WeatherService {
    void fetchCurrentForecast(Listener listener, String city, String unit);

    public interface Listener {
        void onResult(CurrentForecast forecast);

        void onError(Throwable throwable);
    }
}
