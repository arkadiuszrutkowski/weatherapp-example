package com.github.weatherapp.ui.weather;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.weatherapp.R;
import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.injection.scope.ActivityScope;
import com.github.weatherapp.ui.base.BaseActivity;
import com.github.weatherapp.ui.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;

public class CurrentWeatherActivity extends BaseActivity<CurrentWeatherMvpView, CurrentWeatherMvpPresenter> implements CurrentWeatherMvpView {
    private static final String TAG = CurrentWeatherActivity.class.getSimpleName();

    @BindView(R.id.edit_text_city)
    EditText cityEditText;

    @BindView(R.id.text_city_name)
    TextView cityNameTextView;

    @BindView(R.id.text_temperature)
    TextView temperatureTextView;

    private Component component;

    private Unbinder unbinder;

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
        new AlertDialog.Builder(this)
                .setTitle("Oops!")
                .setMessage("Something went wrong...")
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }

        return true;
    }

    @Override
    protected CurrentWeatherMvpPresenter newPresenter() {
        return getComponent().presenter();
    }

    @Override
    protected CurrentWeatherMvpView getPresenterView() {
        return this;
    }

    @OnEditorAction(R.id.edit_text_city)
    boolean onEditorActionCityEditText(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.showCurrentForecast(cityEditText.getText().toString());
            return true;
        }
        return false;
    }

    @OnClick(R.id.button_search)
    void onClickSearchButton() {
        presenter.showCurrentForecast(cityEditText.getText().toString());
    }

    private Component getComponent() {
        if (component == null) {
            component = DaggerCurrentWeatherActivity_Component.builder()
                    .appComponent(getAppComponent())
                    .build();
        }
        return component;
    }

    @ActivityScope
    @dagger.Component(dependencies = AppComponent.class)
    public interface Component {
        CurrentWeatherMvpPresenter presenter();
    }
}
