package com.github.weatherapp.ui.weather

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bumptech.glide.Glide
import com.github.weatherapp.R
import com.github.weatherapp.core.AppSettings
import com.github.weatherapp.injection.component.AppComponent
import com.github.weatherapp.injection.scope.ActivityScope
import com.github.weatherapp.ui.base.BaseActivity
import com.github.weatherapp.ui.settings.SettingsActivity

class CurrentWeatherActivity : BaseActivity<CurrentWeatherMvpView, CurrentWeatherMvpPresenter>(), CurrentWeatherMvpView {

    private var cityEditText: EditText? = null
    private var cityNameTextView: TextView? = null
    private var temperatureTextView: TextView? = null

    private val component: Component by lazy {
        DaggerCurrentWeatherActivity_Component.builder()
                .appComponent(appComponent)
                .build()
    }

    private var unbinder: Unbinder? = null

    override fun updateCurrentForecast(model: CurrentWeatherViewModel) {
        Log.d(TAG, model.toString())
        cityNameTextView!!.text = model.cityName
        temperatureTextView!!.text = model.temperature
        Glide.with(this)
                .load(model.weatherIconPath)
                .into(findViewById(R.id.image_weather_icon) as ImageView)
    }

    override fun updateErrorMessage(message: String) {
        AlertDialog.Builder(this@CurrentWeatherActivity)
                .setTitle("Oops!")
                .setMessage("Something went wrong...")
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unbinder = ButterKnife.bind(this)
        initViews()
    }

    override fun onDestroy() {
        unbinder!!.unbind()
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

    private fun initViews() {
        cityEditText = findViewById(R.id.edit_text_city) as EditText
        cityNameTextView = findViewById(R.id.text_city_name) as TextView
        temperatureTextView = findViewById(R.id.text_temperature) as TextView
        val searchButton = findViewById(R.id.button_search) as ImageButton

        cityEditText!!.setOnEditorActionListener { textView, i, keyEvent -> onEditorActionCityEditText(i) }

        searchButton.setOnClickListener { onClickSearchButton() }
    }

    private fun onEditorActionCityEditText(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val unit = component.appSettings().metricUnit
            presenter.showCurrentForecast(cityEditText!!.text.toString(), unit)
            return true
        }
        return false
    }

    private fun onClickSearchButton() {
        presenter.showCurrentForecast(cityEditText!!.text.toString(), "metric")
    }

    @ActivityScope
    @dagger.Component(dependencies = arrayOf(AppComponent::class))
    interface Component {
        fun presenter(): CurrentWeatherMvpPresenter

        fun appSettings(): AppSettings
    }

    companion object {
        private val TAG = CurrentWeatherActivity::class.java.simpleName
    }
}
