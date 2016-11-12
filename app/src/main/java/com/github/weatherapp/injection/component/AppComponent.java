package com.github.weatherapp.injection.component;

import android.content.Context;

import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.injection.module.AppModule;
import com.github.weatherapp.injection.module.NetworkingModule;
import com.github.weatherapp.injection.module.OpenWeatherApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkingModule.class, OpenWeatherApiModule.class})
public interface AppComponent {
    Context appContext();

    WeatherService weatherService();
}
