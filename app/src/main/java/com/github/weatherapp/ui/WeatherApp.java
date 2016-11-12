package com.github.weatherapp.ui;

import android.app.Application;
import android.content.Context;

import com.github.weatherapp.BuildConfig;
import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.injection.component.DaggerAppComponent;
import com.github.weatherapp.injection.module.AppModule;
import com.github.weatherapp.injection.module.NetworkingModule;
import com.github.weatherapp.injection.module.OpenWeatherApiModule;

public class WeatherApp extends Application {
    private AppComponent appComponent;

    public static WeatherApp get(Context context) {
        return (WeatherApp) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) initAppComponent();

        return appComponent;
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkingModule(new NetworkingModule("http://api.openweathermap.org/data/2.5/"))
                .openWeatherApiModule(new OpenWeatherApiModule(BuildConfig.WEATHER_APP_ID))
                .build();
    }
}
