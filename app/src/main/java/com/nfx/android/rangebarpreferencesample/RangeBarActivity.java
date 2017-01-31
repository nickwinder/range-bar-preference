package com.nfx.android.rangebarpreferencesample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * NFX Development
 * Created by nick on 29/01/17.
 */
public class RangeBarActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new RangeBarFragment()).commit();
    }

    public static class RangeBarFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {
        private static final String TAG = RangeBarFragment.class.getName();
        final Preference.OnPreferenceChangeListener preferenceChangeListener =
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        Log.i(TAG, "onPreferenceChange called Preference Change Key : " +
                                getString(R.string.range_bar) + " value : " + newValue);
                        return true;
                    }
                };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_range_bar);
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            Preference preference = getPreferenceManager().findPreference(getString(R.string.range_bar));
            preference.setOnPreferenceChangeListener(preferenceChangeListener);
        }

        @Override
        public void onPause() {
            super.onPause();

            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            Preference preference = getPreferenceManager().findPreference(getString(R.string.range_bar));
            preference.setOnPreferenceChangeListener(null);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i(TAG, "onSharedPreferenceChanged called Preference Change Key : " +
                    key + " value : " + sharedPreferences.getString(key, "N/A"));
        }
    }
}
