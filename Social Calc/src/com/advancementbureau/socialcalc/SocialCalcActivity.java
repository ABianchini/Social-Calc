package com.advancementbureau.socialcalc;

import com.advancementbureau.socialcalc.ArithmeticFragment.OnButtonSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SocialCalcActivity extends Activity {
	
	//TextView calcView = (TextView) findViewById(R.id.calc_bar);
	OnButtonSelectedListener mListener;
	public static String calcBar = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	getMenuInflater().inflate(R.menu.mainoptions, menu);
    	menu.findItem(R.id.help_menu_item).setIntent(new Intent(this, HelpActivity.class));
    	menu.findItem(R.id.settings_menu_item).setIntent(new Intent(this, SettingsActivity.class));
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	if (item.getItemId() == R.id.settings_menu_item) {
    			startActivity(item.getIntent()); }
    	if (item.getItemId() == R.id.help_menu_item) {
			startActivity(item.getIntent()); }
    	return true;
    }
    
    public void onButtonSelected(int id) {
    	if (id == 0) {
    		PopUp(R.string.eight, R.string.delete);
    	}
    }
    
    public void PopUp(int title, int message){
        new AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Close", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        }).show();
    }
}