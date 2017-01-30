package com.nfx.android.rangebarpreferencesample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

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

    /**
     * This fragment shows the preferences for the first header.
     */
    public static class RangeBarFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_range_bar);
        }
    }

}
