package com.github.weatherapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity
        implements BaseView {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for retained presenter
        if (getLastCustomNonConfigurationInstance() != null) {
            presenter = (P) getLastCustomNonConfigurationInstance();
        } else  {
            Log.d("BaseActivity", "newPresenter");
            presenter = newPresenter();
        }

        presenter.attach(getPresenterView());
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d("BaseActivity", "onRetainCustomNonConfigurationInstance");
        return presenter;
    }

    protected abstract P newPresenter();

    protected abstract V getPresenterView();
}
