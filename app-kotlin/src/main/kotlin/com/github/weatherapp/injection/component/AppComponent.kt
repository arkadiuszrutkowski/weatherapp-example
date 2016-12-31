package com.github.weatherapp.injection.component

import com.github.weatherapp.core.AppSettings
import com.github.weatherapp.core.WeatherService
import com.github.weatherapp.injection.module.AppModule
import com.github.weatherapp.injection.module.NetworkingModule
import com.github.weatherapp.injection.module.OpenWeatherApiModule
import com.github.weatherapp.ui.resource.MessageResourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkingModule::class, OpenWeatherApiModule::class))
interface AppComponent {
    fun weatherService(): WeatherService

    fun messageResourceProvider(): MessageResourceProvider

    fun appSettings(): AppSettings
}
