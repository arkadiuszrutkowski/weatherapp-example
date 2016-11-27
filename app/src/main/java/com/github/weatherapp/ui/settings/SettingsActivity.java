package com.github.weatherapp.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import com.github.weatherapp.R;
import com.github.weatherapp.common.util.CollectionUtils;
import com.github.weatherapp.core.AppSettings;
import com.github.weatherapp.ui.WeatherApp;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFERENCE_METRIC = "PREFERENCE_METRIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        AppSettings appSettings = WeatherApp.get(this).getAppComponent().appSettings();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, AppSettingsFragment.newInstance(appSettings.getMetricUnit()))
                .commit();
    }

    public static class AppSettingsFragment extends PreferenceFragment implements
            SharedPreferences.OnSharedPreferenceChangeListener {
        private static final String EXTRA_STRING_UNIT = "EXTRA_STRING_UNIT";

        public static AppSettingsFragment newInstance(String defaultUnit) {
            Bundle args = new Bundle();
            args.putCharSequence(EXTRA_STRING_UNIT, defaultUnit);

            AppSettingsFragment fragment = new AppSettingsFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);

            ListPreference unitPreference = (ListPreference) findPreference(PREFERENCE_METRIC);
            CharSequence entryName = unitPreference.getEntry();
            if (entryName == null) {
                CharSequence value = getArguments().getCharSequence(EXTRA_STRING_UNIT);
                int entryIndex = CollectionUtils.indexOf(unitPreference.getEntryValues(), value);
                entryName = unitPreference.getEntries()[entryIndex];
                unitPreference.setValueIndex(entryIndex);
            }
            unitPreference.setSummary(entryName);
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference preference = findPreference(key);
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                listPreference.setSummary(listPreference.getEntry());
            }
        }
    }
}
