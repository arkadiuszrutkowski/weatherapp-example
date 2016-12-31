package com.github.weatherapp.ui.adapter

import android.content.Context
import android.widget.ArrayAdapter

class MetricUnitAdapter(context: Context, resource: Int, private val metricUnitValues: Array<String>) : ArrayAdapter<String>(context, resource) {

    override fun getCount(): Int {
        return metricUnitValues.size
    }

    override fun getItem(position: Int): String? {
        return metricUnitValues[position]
    }
}
