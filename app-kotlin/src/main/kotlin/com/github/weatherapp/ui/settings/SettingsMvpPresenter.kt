package com.github.weatherapp.ui.settings

import com.github.weatherapp.core.AppSettings
import com.github.weatherapp.injection.scope.ActivityScope
import com.github.weatherapp.ui.base.BaseMvpPresenter
import com.github.weatherapp.ui.resource.MessageResourceProvider

import javax.inject.Inject

@ActivityScope class SettingsMvpPresenter
@Inject
constructor(private val appSettings: AppSettings, private val resourceProvider: MessageResourceProvider) : BaseMvpPresenter<SettingsMvpView>() {

    fun showAppSettings() {
            val metricUnit = appSettings.metricUnit
            view?.updateSettings(
                    SettingsViewModel(resourceProvider.getMetricUnitDescription(metricUnit)))
    }

    fun updateMetricUnit(unit: String) {
        appSettings.saveMetricUnit(unit)
        showAppSettings()
    }
}
