package com.github.weatherapp.ui.weather

import com.github.weatherapp.core.CurrentForecast
import com.github.weatherapp.core.WeatherService
import com.github.weatherapp.injection.scope.ActivityScope
import com.github.weatherapp.ui.base.BaseMvpPresenter
import com.github.weatherapp.ui.resource.MessageResourceProvider

import javax.inject.Inject

@ActivityScope class CurrentWeatherMvpPresenter
@Inject
constructor(private val service: WeatherService, private val provider: MessageResourceProvider) : BaseMvpPresenter<CurrentWeatherMvpView>() {

    fun showCurrentForecast(city: String, unit: String) {
        service.fetchCurrentForecast(object : WeatherService.Listener {
            override fun onResult(forecast: CurrentForecast) {
                    view?.updateCurrentForecast(CurrentWeatherViewModel(
                            provider.getCityDescription(forecast),
                            provider.getTemperatureDescription(forecast),
                            String.format(WEATHER_ICON_URL_FORMAT, forecast.weatherList!![0].icon)))
            }

            override fun onError(throwable: Throwable) {
                    view?.updateErrorMessage(throwable.message!!)
            }
        }, city, unit)
    }

    companion object {
        private val WEATHER_ICON_URL_FORMAT = "http://openweathermap.org/img/w/%s.png"
    }
}
