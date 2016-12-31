package com.github.weatherapp.ui.base

/*
    Basic presenter functionality
 */
open class BaseMvpPresenter<V : BaseMvpView> {
    protected var view: V? = null
        private set

    fun attach(view: V) {
        this.view = view
    }

    fun detach() {
        view = null
    }
}
