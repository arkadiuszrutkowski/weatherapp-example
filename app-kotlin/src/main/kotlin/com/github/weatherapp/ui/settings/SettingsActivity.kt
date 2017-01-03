package com.github.weatherapp.ui.settings

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.TextView
import butterknife.*
import com.github.weatherapp.R
import com.github.weatherapp.injection.component.AppComponent
import com.github.weatherapp.injection.scope.ActivityScope
import com.github.weatherapp.ui.base.BaseActivity

class SettingsActivity : BaseActivity<SettingsMvpView, SettingsMvpPresenter>(), SettingsMvpView {

    @BindView(R.id.summary_temperature_unit)
    internal lateinit var temperatureUnitTextView: TextView

    @BindArray(R.array.temperature_unit_values)
    internal lateinit var temperatureUnitValues: Array<String>

    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        unbinder = ButterKnife.bind(this)

        presenter.showAppSettings()
    }

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }

    override fun newPresenter(): SettingsMvpPresenter {
        return component.presenter()
    }

    override val presenterView: SettingsMvpView
        get() = this

    private val component: Component by lazy {
        DaggerSettingsActivity_Component.builder()
                .appComponent(appComponent)
                .build()
    }

    override fun updateSettings(model: SettingsViewModel) {
        temperatureUnitTextView.text = model.metricUnit
    }

    @OnClick(R.id.preference_temperature_unit)
    internal fun onClickPreferenceTemperatureUnit() {
        AlertDialog.Builder(this)
                .setSingleChoiceItems(
                        R.array.temperature_unit_descriptions,
                        0,
                        { dialogInterface, i -> presenter.updateMetricUnit(temperatureUnitValues[i]) })
                .show()
    }

    @ActivityScope
    @dagger.Component(dependencies = arrayOf(AppComponent::class))
    interface Component {
        fun presenter(): SettingsMvpPresenter
    }
}
