package com.github.weatherapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.weatherapp.injection.component.AppComponent;
import com.github.weatherapp.ui.WeatherApp;

/**
 * Base activity for handling presenter's lifecycle and provides some utilities like Dagger components.
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends AppCompatActivity
        implements BaseMvpView {

    protected P presenter;

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for persisting presenter
        if (getLastCustomNonConfigurationInstance() != null) {
            presenter = (P) getLastCustomNonConfigurationInstance();
        } else {
            presenter = newPresenter();
        }

        presenter.attach(getPresenterView());
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    protected AppComponent getAppComponent() {
        return WeatherApp.get(this).getAppComponent();
    }

    protected abstract P newPresenter();

    protected abstract V getPresenterView();
}
