package com.github.weatherapp.injection.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Module for general purpose networking
 */
@Module
public class NetworkingModule {
    private final String apiUrl;

    public NetworkingModule(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /*
        Custom configured Gson instance. Feel free to change!
     */
    @Provides
    @Singleton
    static Gson provideGson() {
        return new Gson();
    }

    /*
        Custom configured OkHttpClient instance. Feel free to change!
     */
    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideOpenWeatherApi(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
