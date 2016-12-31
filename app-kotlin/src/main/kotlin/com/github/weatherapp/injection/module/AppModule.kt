package com.github.weatherapp.injection.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.weatherapp.core.AppSettings
import com.github.weatherapp.data.SharedPrefsAppSettings
import com.github.weatherapp.ui.resource.AndroidMessageResourceProvider
import com.github.weatherapp.ui.resource.MessageResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
    Android specific application module
 */
@Module
class AppModule(appContext: Context) {
    private val appContext: Context

    init {
        this.appContext = appContext.applicationContext
    }

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return appContext
    }

        @Provides
        @Singleton
        fun provideMessageResourceProvider(context: Context): MessageResourceProvider {
            return AndroidMessageResourceProvider(context)
        }

        @Provides
        @Singleton
        fun provideAppSettings(preferences: SharedPreferences): AppSettings {
            return SharedPrefsAppSettings(preferences)
        }

        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
}
