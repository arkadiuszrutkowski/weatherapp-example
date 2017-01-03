package com.github.weatherapp.ui.weather

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import butterknife.*
import com.bumptech.glide.Glide
import com.github.weatherapp.R
import com.github.weatherapp.injection.component.AppComponent
import com.github.weatherapp.injection.scope.ActivityScope
import com.github.weatherapp.ui.base.BaseActivity
import com.github.weatherapp.ui.settings.SettingsActivity

class CurrentWeatherActivity : BaseActivity<CurrentWeatherMvpView, CurrentWeatherMvpPresenter>(), CurrentWeatherMvpView {

    @BindView(R.id.edit_text_city)
    internal lateinit var cityEditText: EditText

    @BindView(R.id.text_city_name)
    internal lateinit var cityNameTextView: TextView

    @BindView(R.id.text_temperature)
    internal lateinit var temperatureTextView: TextView

    private val component: Component by lazy {
        DaggerCurrentWeatherActivity_Component.builder()
                .appComponent(appComponent)
                .build()
    }

    private lateinit var unbinder: Unbinder

    override fun updateCurrentForecast(model: CurrentWeatherViewModel) {
        Log.d(TAG, model.toString())
        cityNameTextView.text = model.cityName
        temperatureTextView.text = model.temperature
        Glide.with(this)
                .load(model.weatherIconPath)
                .into(findViewById(R.id.image_weather_icon) as ImageView)
    }

    override fun updateErrorMessage(message: String) {
        AlertDialog.Builder(this)
                .setTitle("Oops!")
                .setMessage("Something went wrong...")
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unbinder = ButterKnife.bind(this)
    }

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.weather, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }

        return true
    }

    override fun newPresenter(): CurrentWeatherMvpPresenter {
        return component.presenter()
    }

    override val presenterView: CurrentWeatherMvpView
        get() = this

    @OnEditorAction(R.id.edit_text_city)
    fun onEditorActionCityEditText(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.showCurrentForecast(cityEditText.text.toString())
            return true
        }
        return false
    }

    @OnClick(R.id.button_search)
    fun onClickSearchButton() {
        presenter.showCurrentForecast(cityEditText.text.toString())
    }

    @ActivityScope
    @dagger.Component(dependencies = arrayOf(AppComponent::class))
    interface Component {
        fun presenter(): CurrentWeatherMvpPresenter
    }

    companion object {
        private val TAG = CurrentWeatherActivity::class.java.simpleName
    }
}
