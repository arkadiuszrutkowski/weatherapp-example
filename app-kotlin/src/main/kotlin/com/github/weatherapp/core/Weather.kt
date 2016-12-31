package com.github.weatherapp.core

class Weather {
    var main: String? = null

    var icon: String? = null

    var description: String? = null

    override fun toString(): String {
        return "Weather{" +
                "weatherData='" + main + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}'
    }
}
