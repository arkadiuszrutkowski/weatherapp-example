package com.github.weatherapp.ui.main;

import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.ui.base.BaseView;

public interface MainView extends BaseView {

    void updateCurrentForecast(CurrentForecast forecast);

    void updateErrorMessage(String message);
}
