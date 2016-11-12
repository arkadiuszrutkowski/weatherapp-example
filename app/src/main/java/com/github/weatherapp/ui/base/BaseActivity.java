package com.github.weatherapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity
        implements BaseView {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for retained presenter
        if (savedInstanceState != null) {
            presenter = PresenterCache.getCache().restore(savedInstanceState);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save presenter during configuration change
        PresenterCache.getCache().save(presenter, outState);
    }

    protected abstract P newPresenter();

    protected abstract V getPresenterView();
}
