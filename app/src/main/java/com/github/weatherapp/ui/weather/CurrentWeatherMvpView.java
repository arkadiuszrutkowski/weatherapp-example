package com.github.weatherapp.ui.weather;

import com.github.weatherapp.ui.base.BaseMvpView;

interface CurrentWeatherMvpView extends BaseMvpView {

    void updateCurrentForecast(CurrentWeatherViewModel model);

    void updateErrorMessage(String message);
}
