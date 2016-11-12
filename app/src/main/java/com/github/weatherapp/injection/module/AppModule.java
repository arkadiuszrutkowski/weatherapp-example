package com.github.weatherapp.injection.module;

import android.content.Context;

import com.github.weatherapp.core.WeatherService;
import com.github.weatherapp.data.OpenWeatherApi;
import com.github.weatherapp.data.OpenWeatherService;
import com.github.weatherapp.ui.resource.AndroidMessageResourceProvider;
import com.github.weatherapp.ui.resource.MessageResourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    static MessageResourceProvider provideMessageResourceProvider(Context context) {
        return new AndroidMessageResourceProvider(context);
    }
}
