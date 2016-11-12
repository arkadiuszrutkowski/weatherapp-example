package com.github.weatherapp.ui.weather;

import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.ui.base.BaseView;

public interface CurrentWeatherView extends BaseView {

    void updateCurrentForecast(CurrentWeatherViewModel model);

    void updateErrorMessage(String message);
}
