package com.github.weatherapp.ui.settings;

import com.github.weatherapp.ui.base.BaseMvpView;

interface SettingsMvpView extends BaseMvpView {
    void updateSettings(SettingsViewModel model);
}
