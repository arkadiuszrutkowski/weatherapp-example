package com.github.weatherapp.ui.settings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import com.github.weatherapp.R;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFERENCE_METRIC = "PREFERENCE_METRIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new AppSettingsFragment())
                .commit();
    }

    public static class AppSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        private ListPreference unitPreference;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);

            unitPreference = (ListPreference) findPreference(PREFERENCE_METRIC);
            unitPreference.setSummary(unitPreference.getEntry());

            getPreferenceScreen().setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            switch (preference.getKey()) {
                case PREFERENCE_METRIC:
                    unitPreference.setSummary(unitPreference.getEntry());
                    break;
            }

            return true;
        }
    }
}
