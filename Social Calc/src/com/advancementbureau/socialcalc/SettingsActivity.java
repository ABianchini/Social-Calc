package com.advancementbureau.socialcalc;

import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.settings);
        if (Build.VERSION.SDK_INT >= 11) {
	        ActionBar actionBar2 = getActionBar();
	        actionBar2.setDisplayHomeAsUpEnabled(true);
        }
    }
    
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers, target);
    }
    
    public static class Prefs1Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Make sure default values are applied.  In a real app, you would
            // want this in a shared function that is used to retrieve the
            // SharedPreferences wherever they are needed.
            PreferenceManager.setDefaultValues(getActivity(), R.xml.advanced_preferences, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragmented_preferences);
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	if (item.getItemId() == android.R.id.home) {
			Intent intent2 = new Intent(this, SocialCalcActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2); }
    	return true;
    }
}