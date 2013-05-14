package com.tafuta.android.app; 

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;



public class TafutaSettings extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settings);
		
		// Register a change listener 
		Context context = getApplicationContext();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
		
	}

	// Inherited abstract method so it must be implemented
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {	
		Log.i("Preferences", "Preferences changed, key="+key);
		if(key.compareTo("editphonePref")==0)
		if(key.compareTo("edituserPref")==0)
		Log.i("Preferences", " Changed phonenumber to " + TafutaSettings.getphone(getApplicationContext()));
		Log.i("Preferences", " Changed username to " + TafutaSettings.getuser(getApplicationContext()));
	}
	
	// Static method to return the preference for whether to receive SMS
	public static boolean getsms(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("sms", false);
	}
	
	// Static method to return the preference for the phone number
	public static String getphone(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getString("editphonePref", "");
	}
	// Static method to return the preference for the user name
	public static String getuser(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getString("edituserPref", "");
	}
}
