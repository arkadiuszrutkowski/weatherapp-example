package com.github.weatherapp.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.weatherapp.core.AppSettings;
import com.github.weatherapp.data.SharedPrefsAppSettings;
import com.github.weatherapp.ui.resource.AndroidMessageResourceProvider;
import com.github.weatherapp.ui.resource.MessageResourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
    Android specific application module
 */
@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides
    @Singleton
    static MessageResourceProvider provideMessageResourceProvider(Context context) {
        return new AndroidMessageResourceProvider(context);
    }

    @Provides
    @Singleton
    static AppSettings provideAppSettings(SharedPreferences preferences) {
        return new SharedPrefsAppSettings(preferences);
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }
}
