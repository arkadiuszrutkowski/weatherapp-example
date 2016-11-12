package com.github.weatherapp.ui.weather;

import com.github.weatherapp.core.CurrentForecast;
import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.data.MessageResourceProvider;
import com.github.weatherapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class CurrentWeatherPresenter extends BasePresenter<CurrentWeatherView> {
    private static final String TAG = CurrentWeatherPresenter.class.getSimpleName();
    private static final String WEATHER_ICON_URL_FORMAT = "http://openweathermap.org/img/w/%s.png";

    private final WeatherService service;
    private final MessageResourceProvider provider;

    @Inject
    public CurrentWeatherPresenter(WeatherService service, MessageResourceProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    public void showCurrentForecast(String city, String unit) {
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
        }, city, unit);
    }
}
