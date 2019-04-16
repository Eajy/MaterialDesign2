package com.eajy.materialdesign2.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.eajy.materialdesign2.App;
import com.eajy.materialdesign2.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences_settings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findPreference("pref_text").setOnPreferenceChangeListener(this);
        findPreference("pref_list").setOnPreferenceChangeListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        onPreferenceChange(findPreference("pref_text"), preferences.getString("pref_text", ""));
        onPreferenceChange(findPreference("pref_list"), preferences.getString("pref_list", ""));
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey() != null) {
            switch (preference.getKey()) {
                case "pref_click":
                    Snackbar.make(getListView(), getString(R.string.pref_on_preference_click), Snackbar.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringValue = newValue.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);
            preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
        } else {
            preference.setSummary(stringValue);
        }
        return true;
    }
}
