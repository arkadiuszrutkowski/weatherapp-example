package com.github.weatherapp.core;

public class Weather {
    public String main;

    public String icon;

    public String description;

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
