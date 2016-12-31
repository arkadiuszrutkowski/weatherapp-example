package com.github.weatherapp.ui.resource

import android.content.Context
import android.support.annotation.ArrayRes
import com.github.weatherapp.R
import com.github.weatherapp.core.CurrentForecast
import java.util.*

class AndroidMessageResourceProvider(private val context: Context) : MessageResourceProvider {

    override fun getCityDescription(forecast: CurrentForecast): String {
        return context.getString(R.string.text_city_country, forecast.city, forecast.country!!.country)
    }

    override fun getTemperatureDescription(forecast: CurrentForecast): String {
        return context.getString(R.string.temperature, java.lang.Double.valueOf(forecast.weatherData!!.temp)!!.toInt())
    }

    override fun getMetricUnitDescription(metricUnit: String): String {
        return getDescriptionStringFromValue(
                metricUnit, R.array.temperature_unit_values, R.array.temperature_unit_descriptions)
    }

    private fun getDescriptionStringFromValue(value: String, @ArrayRes valueArrayResId: Int, @ArrayRes descArrayResId: Int): String {
        val unitValues = context.resources.getStringArray(valueArrayResId)
        val units = context.resources.getStringArray(descArrayResId)

        val unitIdx = Arrays.asList(*unitValues).indexOf(value)

        return units[unitIdx]
    }
}
