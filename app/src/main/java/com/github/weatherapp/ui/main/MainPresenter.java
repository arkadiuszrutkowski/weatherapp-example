package com.github.weatherapp.ui.main;

import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainView> {
    private static final String TAG = MainPresenter.class.getSimpleName();

    private final WeatherService service;

    @Inject
    public MainPresenter(WeatherService service) {
        this.service = service;
    }

    public void showCurrentForecast(String city, String unit) {
        service.fetchCurrentForecast(new WeatherService.Listener() {
            @Override
            public void onResult(CurrentForecast forecast) {
                if (isViewAttached()) {
                    getView().updateCurrentForecast(forecast);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().updateErrorMessage(throwable.getMessage());
                }
            }
        }, city, unit);
    }
}
