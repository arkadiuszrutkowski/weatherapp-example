package com.github.weatherapp.injection.module

import com.github.weatherapp.core.WeatherService
import com.github.weatherapp.data.OpenWeatherApi
import com.github.weatherapp.data.OpenWeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/*
    Open Weather Map API module
 */
@Module
class OpenWeatherApiModule(private val apiKey: String) {

    @Provides
    @Singleton
    fun provideWeatherService(api: OpenWeatherApi): WeatherService {
        return OpenWeatherService(api, apiKey)
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(retrofit: Retrofit): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }
}
