package com.github.weatherapp.injection.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
    Module for general purpose networking
 */
@Module
class NetworkingModule(private val apiUrl: String) {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    /*
    Custom configured Gson instance. Feel free to change!
 */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    /*
    Custom configured OkHttpClient instance. Feel free to change!
 */
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return logging
    }
}
