package com.github.weatherapp.ui.base;

/*
    Basic presenter functionality
 */
public class BaseMvpPresenter<V extends BaseMvpView> {
    private V view;

    public void attach(V view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }

    protected V getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }
}
