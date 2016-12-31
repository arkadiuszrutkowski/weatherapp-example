package com.github.weatherapp.ui

import android.app.Application
import android.content.Context

import com.github.weatherapp.BuildConfig
import com.github.weatherapp.injection.component.AppComponent
import com.github.weatherapp.injection.component.DaggerAppComponent
import com.github.weatherapp.injection.module.AppModule
import com.github.weatherapp.injection.module.NetworkingModule
import com.github.weatherapp.injection.module.OpenWeatherApiModule

class WeatherApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkingModule(NetworkingModule("http://api.openweathermap.org/data/2.5/"))
                .openWeatherApiModule(OpenWeatherApiModule(BuildConfig.WEATHER_APP_ID))
                .build()
    }

    companion object {
        operator fun get(context: Context): WeatherApp {
            return context.applicationContext as WeatherApp
        }
    }
}
