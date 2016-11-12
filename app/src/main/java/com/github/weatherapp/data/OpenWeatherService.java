package com.github.weatherapp.data;

import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.core.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenWeatherService implements WeatherService {
    private final OpenWeatherApi api;
    private final String apiKey;

    public OpenWeatherService(OpenWeatherApi api, String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public void fetchCurrentForecast(final Listener listener, String city, String unit) {
        api.getCurrentWeatherForecast(apiKey, city, unit).enqueue(new Callback<CurrentForecast>() {
            @Override
            public void onResponse(Call<CurrentForecast> call, Response<CurrentForecast> response) {
                listener.onResult(response.body());
            }

            @Override
            public void onFailure(Call<CurrentForecast> call, Throwable t) {
                listener.onError(t);
            }
        });
    }
}
