package com.github.weatherapp.injection.module;

import android.content.Context;

import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.data.OpenWeatherApi;
import com.github.weatherapp.data.OpenWeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }
}
