package com.github.weatherapp.injection.module;

import android.content.Context;

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
    Context provideAppContext() {
        return appContext;
    }
}
