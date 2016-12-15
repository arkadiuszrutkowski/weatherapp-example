package com.github.weatherapp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

public class MetricUnitAdapter extends ArrayAdapter<String> {
    private final String[] metricUnitValues;

    public MetricUnitAdapter(Context context, int resource, String[] metricUnitValues) {
        super(context, resource);
        this.metricUnitValues = metricUnitValues;

    }

    @Override
    public int getCount() {
        return metricUnitValues.length;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return metricUnitValues[position];
    }
}
