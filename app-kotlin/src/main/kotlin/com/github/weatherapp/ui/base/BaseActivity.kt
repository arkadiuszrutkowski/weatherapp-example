package com.github.weatherapp.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.weatherapp.injection.component.AppComponent
import com.github.weatherapp.ui.WeatherApp

/**
 * Base activity for handling presenter's lifecycle and provides some utilities like Dagger components.
 * @param <V>
 * *
 * @param <P>
</P></V> */
abstract class BaseActivity<V : BaseMvpView, P : BaseMvpPresenter<V>> : AppCompatActivity(), BaseMvpView {
    protected lateinit var presenter: P

    protected val appComponent: AppComponent
        get() = WeatherApp[this].appComponent

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check for persisting presenter
        presenter = lastNonConfigurationInstance as P? ?: newPresenter()
        presenter.attach(presenterView)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    protected abstract fun newPresenter(): P

    protected abstract val presenterView: V
}
