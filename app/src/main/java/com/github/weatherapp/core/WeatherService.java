package com.github.weatherapp.core;

public interface WeatherService {
    void fetchCurrentForecast(Listener listener, String city, String unit);

    interface Listener {
        void onResult(CurrentForecast forecast);

        void onError(Throwable throwable);
    }
}
