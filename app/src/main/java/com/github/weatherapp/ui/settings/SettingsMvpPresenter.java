package com.github.weatherapp.ui.settings;

import com.github.weatherapp.core.AppSettings;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.base.BaseMvpPresenter;
import com.github.weatherapp.ui.resource.MessageResourceProvider;

import javax.inject.Inject;

@ActivityScope
class SettingsMvpPresenter extends BaseMvpPresenter<SettingsMvpView> {
    private final AppSettings appSettings;
    private final MessageResourceProvider resourceProvider;

    @Inject
    SettingsMvpPresenter(AppSettings appSettings, MessageResourceProvider resourceProvider) {
        this.appSettings = appSettings;
        this.resourceProvider = resourceProvider;
    }

    void showAppSettings() {
        if (isViewAttached()) {
            String metricUnit = appSettings.getMetricUnit();
            getView().updateSettings(
                    new SettingsViewModel(resourceProvider.getMetricUnitDescription(metricUnit)));
        }
    }
    
    void updateMetricUnit(String unit) {
        appSettings.setMetricUnit(unit);
        showAppSettings();
    }
}
