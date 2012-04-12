package com.advancementbureau.socialcalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SocialCalcActivity extends Activity {
	
	public static String calcBar = "";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //FragmentManager fm = getFragmentManager();
        //addShowHideListener(R.id.moreButton, fm.findFragmentById(R.id.arithmeticFragment));
    }

    public static class ArithmeticFragment extends Fragment {
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.arithmetic, container, false);
    		Button zeroButton = (Button) v.findViewById(R.id.buttonZero);
    		final TextView calcBar = (TextView) getActivity().findViewById(R.id.calc_bar);
    		zeroButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcBar.setText("0");
    			}
    		});
    		return v;
    	}
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