package com.github.weatherapp.ui.weather;

import com.github.weatherapp.ui.base.BaseView;

interface CurrentWeatherView extends BaseView {

    void updateCurrentForecast(CurrentWeatherViewModel model);

    void updateErrorMessage(String message);
}
