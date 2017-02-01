package com.nfx.android.rangebarpreferencesample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * NFX Development
 * Created by nick on 29/01/17.
 */
public class RangeBarActivityCompat extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_bar_layout);

        getFragmentManager().beginTransaction().replace(R.id.preferences_fragment,
                new RangeBarFragmentCompat()).commit();

        Button button = (Button) findViewById(R.id.switch_mode_button);
        button.setText(getString(R.string.switch_to_sdk));
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Implement back button in code
    }

    public static class RangeBarFragmentCompat extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {
        private static final String TAG = RangeBarFragmentCompat.class.getName();
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
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_range_bar);
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceManager().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
            Preference preference = getPreferenceManager().findPreference(getString(R.string
                    .range_bar));
            preference.setOnPreferenceChangeListener(preferenceChangeListener);
        }

        @Override
        public void onPause() {
            super.onPause();

            getPreferenceManager().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
            Preference preference = getPreferenceManager().findPreference(getString(R.string
                    .range_bar));
            preference.setOnPreferenceChangeListener(null);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i(TAG, "onSharedPreferenceChanged called Preference Change Key : " +
                    key + " value : " + sharedPreferences.getString(key, "N/A"));
        }
    }
}
