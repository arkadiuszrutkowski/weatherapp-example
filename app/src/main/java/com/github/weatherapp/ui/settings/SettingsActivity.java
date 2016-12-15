package com.github.weatherapp.ui.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.github.weatherapp.R;
import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.base.BaseActivity;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingsActivity extends BaseActivity<SettingsMvpView, SettingsMvpPresenter> implements SettingsMvpView {
    @BindView(R.id.summary_temperature_unit)
    TextView temperatureUnitTextView;
    @BindArray(R.array.temperature_unit_values)
    String[] temperatureUnitValues;

    private Component component;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        unbinder = ButterKnife.bind(this);

        presenter.showAppSettings();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
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

    @OnClick(R.id.preference_temperature_unit)
    void onClickPreferenceTemperatureUnit() {
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(R.array.temperature_unit_descriptions, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.updateMetricUnit(temperatureUnitValues[i]);
                    }
                })
        .show();
    }

    @ActivityScope
    @dagger.Component(dependencies = AppComponent.class)
    public interface Component {
        SettingsMvpPresenter presenter();
    }
}
