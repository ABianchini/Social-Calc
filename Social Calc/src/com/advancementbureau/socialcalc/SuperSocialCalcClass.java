package com.advancementbureau.socialcalc;

import android.app.Activity;
import android.content.SharedPreferences;

public class SuperSocialCalcClass extends Activity {
	
	public static final String GAME_PREFERENCES = "GamePrefs";
	
	public static final String PREFERENCES_NOTIFICATION = "notif";
	
	public static final String PRE_TEXT = "text";
	public static final String FIRST_BOOT = "boot";
	public static final String DEFCON = "currentState";
	public static final String CALCS = "calcs";
	SharedPreferences mGameSettings;
	
	
	String FILENAME = "log.txt";
	 
}