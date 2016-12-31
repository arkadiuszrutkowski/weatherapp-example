package com.github.weatherapp.ui.settings

import com.github.weatherapp.ui.base.BaseMvpView

interface SettingsMvpView : BaseMvpView {
    fun updateSettings(model: SettingsViewModel)
}
