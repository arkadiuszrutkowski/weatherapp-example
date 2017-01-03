package com.github.weatherapp.ui.weather;

import com.github.weatherapp.core.AppSettings;
import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.base.BaseMvpPresenter;
import com.github.weatherapp.ui.resource.MessageResourceProvider;

import javax.inject.Inject;

@ActivityScope
class CurrentWeatherMvpPresenter extends BaseMvpPresenter<CurrentWeatherMvpView> {
    private static final String WEATHER_ICON_URL_FORMAT = "http://openweathermap.org/img/w/%s.png";

    private final WeatherService service;
    private final MessageResourceProvider provider;
    private final AppSettings appSettings;

    @Inject
    CurrentWeatherMvpPresenter(WeatherService service, MessageResourceProvider provider, AppSettings appSettings) {
        this.service = service;
        this.provider = provider;
        this.appSettings = appSettings;
    }

    void showCurrentForecast(String city) {
        service.fetchCurrentForecast(new WeatherService.Listener() {
            @Override
            public void onResult(CurrentForecast forecast) {
                if (isViewAttached()) {
                    getView().updateCurrentForecast(new CurrentWeatherViewModel(
                            provider.getCityDescription(forecast),
                            provider.getTemperatureDescription(forecast),
                            String.format(WEATHER_ICON_URL_FORMAT, forecast.weatherList.get(0).icon)));
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (isViewAttached()) {
                    getView().updateErrorMessage(throwable.getMessage());
                }
            }
        }, city, appSettings.getMetricUnit());
    }
}
