package com.github.weatherapp.ui.weather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.weatherapp.R;
import com.github.weatherapp.core.AppSettings;
import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.WeatherApp;
import com.github.weatherapp.ui.base.BaseActivity;

public class CurrentWeatherActivity extends BaseActivity<CurrentWeatherView, CurrentWeatherPresenter> implements CurrentWeatherView {
    private static final String TAG = CurrentWeatherActivity.class.getSimpleName();

    private EditText cityEditText;
    private TextView cityNameTextView;
    private TextView temperatureTextView;

    private Component component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        cityEditText = (EditText) findViewById(R.id.edit_text_city);
        cityNameTextView = (TextView) findViewById(R.id.text_city_name);
        temperatureTextView = (TextView) findViewById(R.id.text_temperature);
        ImageButton searchButton = (ImageButton) findViewById(R.id.button_search);

        cityEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return onEditorActionCityEditText(i);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSearchButton();
            }
        });
    }

    @Override
    public void updateCurrentForecast(CurrentWeatherViewModel model) {
        Log.d(TAG, model.toString());
        cityNameTextView.setText(model.getCityName());
        temperatureTextView.setText(model.getTemperature());
        Glide.with(this)
                .load(model.getWeatherIconPath())
                .into((ImageView) findViewById(R.id.image_weather_icon));
    }

    @Override
    public void updateErrorMessage(String message) {
        new AlertDialog.Builder(CurrentWeatherActivity.this)
                .setTitle("Oops!")
                .setMessage("Something went wrong...")
                .show();
    }

    @Override
    protected CurrentWeatherPresenter newPresenter() {
        return getComponent().presenter();
    }

    @Override
    protected CurrentWeatherView getPresenterView() {
        return this;
    }

    private boolean onEditorActionCityEditText(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String unit = component.appSettings().getMetricUnit();
            presenter.showCurrentForecast(cityEditText.getText().toString(), unit);
            return true;
        }
        return false;
    }

    private void onClickSearchButton() {
        String unit = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("PREFERENCE_UNIT", "metric");
        presenter.showCurrentForecast(cityEditText.getText().toString(), unit);
    }

    private Component getComponent() {
        if (component == null) {
            component = DaggerCurrentWeatherActivity_Component.builder()
                    .appComponent(WeatherApp.get(this).getAppComponent())
                    .build();
        }
        return component;
    }

    @ActivityScope
    @dagger.Component(dependencies = AppComponent.class)
    public interface Component {
        CurrentWeatherPresenter presenter();

        AppSettings appSettings();
    }
}
