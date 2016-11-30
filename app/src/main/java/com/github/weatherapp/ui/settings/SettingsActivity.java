package com.github.weatherapp.ui.settings;

import android.os.Bundle;
import android.widget.TextView;

import com.github.weatherapp.R;
import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.base.BaseActivity;

public class SettingsActivity extends BaseActivity<SettingsMvpView, SettingsMvpPresenter> implements SettingsMvpView {
    private Component component;

    private TextView temperatureUnitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        temperatureUnitTextView = (TextView) findViewById(R.id.summary_temperature_unit);

        presenter.showAppSettings();
    }

    @Override
    protected SettingsMvpPresenter newPresenter() {
        return getComponent().presenter();
    }

    @Override
    protected SettingsMvpView getPresenterView() {
        return this;
    }

    private Component getComponent() {
        if (component == null) {
            component = DaggerSettingsActivity_Component.builder()
                    .appComponent(getAppComponent())
                    .build();
        }
        return component;
    }

    @Override
    public void updateSettings(SettingsViewModel model) {
        temperatureUnitTextView.setText(model.getMetricUnit());
    }

    @ActivityScope
    @dagger.Component(dependencies = AppComponent.class)
    public interface Component {
        SettingsMvpPresenter presenter();
    }
}
