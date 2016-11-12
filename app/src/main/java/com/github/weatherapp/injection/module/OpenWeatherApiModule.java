package com.github.weatherapp.injection.module;

import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.data.OpenWeatherApi;
import com.github.weatherapp.data.OpenWeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class OpenWeatherApiModule {
    private final String apiKey;

    public OpenWeatherApiModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    static OpenWeatherApi provideOpenWeatherApi(Retrofit retrofit) {
        return retrofit.create(OpenWeatherApi.class);
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(OpenWeatherApi api) {
        return new OpenWeatherService(api, apiKey);
    }
}
