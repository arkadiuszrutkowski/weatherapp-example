package com.github.weatherapp.ui.weather

import com.github.weatherapp.ui.base.BaseMvpView

interface CurrentWeatherMvpView : BaseMvpView {

    fun updateCurrentForecast(model: CurrentWeatherViewModel)

    fun updateErrorMessage(message: String)
}
