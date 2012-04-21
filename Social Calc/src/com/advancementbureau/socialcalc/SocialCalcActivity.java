package com.advancementbureau.socialcalc;

import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import api.Twitter;

public class SocialCalcActivity extends SuperSocialCalcClass implements OnDismissListener {
	
	public static String calcString = "";
	public static String shareString = "You haven\'t done a calculation yet.";
	public static String calcs = "";
	public static int decPlace = 4;
	public static boolean radDeg;
	public static boolean twitterLogIn = false;
	private Twitter twitter;
	final String consumerKey = "5Uq334X9fAM9k4RStEpOA";
	final String consumerSecret = "evwBErwNeNmxkZrKkbkZUwrWYS1LtbSLsWMcaILyPU";
	final String callbackURL = "http://www.someurl.com";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
        	public boolean onLongClick(View view) {
        		calcs = "";
				display();
        		return true;
        	}
        });
        mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        if (mGameSettings.contains(CALCS)) {
			calcs = mGameSettings.getString(CALCS, "");
		}

		display();
        
        FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
		FragmentManager fm = getFragmentManager();
		final Fragment arithmeticFragment = fm.findFragmentById(R.id.arithmeticFragment);
		final Fragment advancedFragment = fm.findFragmentById(R.id.advancedFragment);
		ft.show(arithmeticFragment);
		ft.hide(advancedFragment);
		ft.commit();
		
        final Button moreButton = (Button) findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
				FragmentManager fm = getFragmentManager();
				Fragment arithmeticFragment = fm.findFragmentById(R.id.arithmeticFragment);
				Fragment advancedFragment = fm.findFragmentById(R.id.advancedFragment);
				if (arithmeticFragment.isHidden()) {
					ft.show(arithmeticFragment);
					ft.hide(advancedFragment);
					changeMoreButton(1);
				} else {
					ft.show(advancedFragment);
					ft.hide(arithmeticFragment);
					changeMoreButton(2);
				}
				ft.commit();
			}
		});
    }
    
    protected void onResume() {
    	super.onResume();
    	mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        if (mGameSettings.contains(CALCS)) {
			calcs = mGameSettings.getString(CALCS, "");
		}
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String tempDec = sharedPrefs.getString("list_decimal", "four");
        if (tempDec.equals("four")) {
        	decPlace = 4;
        }
        if (tempDec.equals("five")) {
        	decPlace = 5;
        }
        if (tempDec.equals("six")) {
        	decPlace = 6;
        }
        if (tempDec.equals("three")) {
        	decPlace = 3;
        }
        radDeg = sharedPrefs.getBoolean("switch_rad_deg", true);
		display();
    }
    
    public static class AdvancedFragment extends Fragment {
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.advanced, container, false);
    		
    		Button sinButton = (Button) v.findViewById(R.id.sinButton);
    		Button cosButton = (Button) v.findViewById(R.id.cosButton);
    		Button tanButton = (Button) v.findViewById(R.id.tanButton);
    		Button lnButton = (Button) v.findViewById(R.id.lnButton);
    		Button logButton = (Button) v.findViewById(R.id.logButton);
    		Button factButton = (Button) v.findViewById(R.id.factorialButton);
    		Button piButton = (Button) v.findViewById(R.id.piButton);
    		Button eButton = (Button) v.findViewById(R.id.eButton);
    		Button powerButton = (Button) v.findViewById(R.id.powerButton);
    		Button parenCloseButton = (Button) v.findViewById(R.id.parenCloseButton);
    		Button rootButton = (Button) v.findViewById(R.id.rootButton);
    		sinButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "i";
    				display();
    			}
    		});
    		cosButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "c";
    				display();
    			}
    		});
    		tanButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "t";
    				display();
    			}
    		});
    		lnButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "l";
    				display();
    			}
    		});
    		logButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "o";
    				display();
    			}
    		});
    		factButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "f";
    				display();
    			}
    		});
    		piButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "p";
    				display();
    			}
    		});
    		eButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "e";
    				display();
    			}
    		});
    		powerButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "u";
    				display();
    			}
    		});
    		parenCloseButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "b";
    				display();
    			}
    		});
    		rootButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "r";
    				display();
    			}
    		});
    		
    		return v;
    	}
    	public void display() {
    		calcString = "";
    		char[] convertString = calcs.toCharArray();
			for (int i = 0; i < convertString.length; i++) {
				char current = convertString[i];
				if (current == '0') {
					calcString = calcString + "0";
				}
				if (current == '.') {
					calcString = calcString + ".";
				}
				if (current == '-') {
					calcString = calcString + "-";
				}
				if (current == '1') {
					calcString = calcString + "1";
				}
				if (current == '2') {
					calcString = calcString + "2";
				}
				if (current == '3') {
					calcString = calcString + "3";
				}
				if (current == '4') {
					calcString = calcString + "4";
				}
				if (current == '5') {
					calcString = calcString + "5";
				}
				if (current == '6') {
					calcString = calcString + "6";
				}
				if (current == '7') {
					calcString = calcString + "7";
				}
				if (current == '8') {
					calcString = calcString + "8";
				}
				if (current == '9') {
					calcString = calcString + "9";
				}
				if (current == 'a') {
					calcString = calcString + "+";
				}
				if (current == 's') {
					calcString = calcString + "-";
				}
				if (current == 'd') {
					calcString = calcString + "/";
				}
				if (current == 'm') {
					calcString = calcString + "x";
				}
				if (current == 'i') {
					calcString = calcString + "sin(";
				}
				if (current == 'c') {
					calcString = calcString + "cos(";
				}
				if (current == 't') {
					calcString = calcString + "tan(";
				}
				if (current == 'l') {
					calcString = calcString + "ln(";
				}
				if (current == 'o') {
					calcString = calcString + "log(";
				}
				if (current == 'f') {
					calcString = calcString + "!";
				}
				if (current == 'p') {
					calcString = calcString + "PI";
				}
				if (current == 'e') {
					calcString = calcString + "e";
				}
				if (current == 'u') {
					calcString = calcString + "^";
				}
				if (current == 'b') {
					calcString = calcString + ")";
				}
				if (current == 'r') {
					calcString = calcString + "^(.5)";
				}
			}
			TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
			calcView.setText(calcString);
    	}
    }

    public static class ArithmeticFragment extends Fragment {
    	
    	public void calculate() {
    		double work1 = 0;
    		double work2 = 0;
    		double work3 = 0;
    		double num1 = 0;
    		double num2 = 0;
    		double num3 = 0;
    		double num4 = 0;
    		int ops = 0;
    		int op1 = 13;
    		int op2 = 13;
    		int op3 = 13;
    		double endAnswer = 0;
    		
    		if(calcs.length() == 1) {
    			if (calcs.equals("p") || calcs.equals("e")) {
    				if (calcs.equals("p")) endAnswer = Math.PI;
    				if (calcs.equals("e")) endAnswer = Math.E;
    			} else {
    				endAnswer = Double.parseDouble(calcs.substring(0,1));
    			}
    		}
    		
    		if(!calcs.equals("")) {
    			char[] calcPieces = calcs.toCharArray();
    			for (int i = 0; i < calcPieces.length; i++) {
    				char current = calcPieces[i];
    				if (current == 'a' || current == 's' || current == 'm' || current == 'd' || current == 'i' || current == 't' || current == 'c') {
    					ops++;
    				}
    			}
    		}
    		
    		if (!calcs.equals("")) {
	    		char[] calcPieces1 = calcs.toCharArray();
	    		calcLoop1:
	    		for (int i = 0; i < calcPieces1.length; i++) {
	    			char current = calcPieces1[i];
	    			if (current == 'a' || current == 's' || current == 'm' || current == 'd' || current == 'i' || current == 'c' || current == 't') {
	    				op1 = i;
	    				if (ops == 1) break calcLoop1;
	    				
	    				for (int j = op1+1; j < calcPieces1.length; j++) {
	    					char current2 = calcPieces1[j];
	    					if (current2 == 'a' || current2 == 's' || current2 == 'm' || current2 == 'd' || current == 'i' || current == 'c' || current == 't') {
	    						op2 = j;
	    						num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    						if (ops == 2) break calcLoop1;
	    						
	    						for (int k = op2+1; k < calcPieces1.length; k++) {
	    	    					char current3 = calcPieces1[k];
	    	    					if (current3 == 'a' || current3 == 's' || current3 == 'm' || current3 == 'd' || current == 'i' || current == 'c' || current == 't') {
	    	    						op3 = k;
	    	    						num3 = Double.parseDouble(calcs.substring(op2+1, op3));
	    	    						if (ops == 3) break calcLoop1;
	    	    					}
	    	    				}
	    					}
	    				}
	    			}
	    		}
	    		if (ops == 1) {
	    			
	    			if (calcs.substring(0, op1).equals("p") || calcs.substring(0, op1).equals("e")) {
	    				if(calcs.substring(0, op1).equals("p")) num1 = Math.PI;
	    				if(calcs.substring(0, op1).equals("e")) num1 = Math.E;
	    			} else {
	    				if (!calcs.substring(0, op1+1).equals("a") && !calcs.substring(0, op1+1).equals("s") && !calcs.substring(0, op1+1).equals("m") && !calcs.substring(0, op1+1).equals("d") && !calcs.substring(0, op1+1).equals("i") && !calcs.substring(0, op1+1).equals("t") && !calcs.substring(0, op1+1).equals("c")) { 
	    					num1 = Double.parseDouble(calcs.substring(0, op1));
	    				}
	    			}
	    			if (calcs.substring(op1+1, calcs.length()).equals("p") || calcs.substring(op1+1, calcs.length()).equals("e")) {
	    				if(calcs.substring(op1+1, calcs.length()).equals("p")) num2 = Math.PI;
	    				if(calcs.substring(op1+1, calcs.length()).equals("e")) num2 = Math.E;
	    			} else {
	    				num2 = Double.parseDouble(calcs.substring(op1+1, calcs.length()));
	    			}
	    			
	    			if (calcPieces1[op1] == 'c') {
	    				if (radDeg){
		    				if (num1 != 0) {
		    					work1 = num1 * Math.cos(num2);
		    				} else {
		    					work1 = Math.cos(num2);
		    				}
	    				} else {
	    					if (num1 != 0) {
		    					work1 = num1 * Math.cos(Math.toRadians(num2));
		    				} else {
		    					work1 = Math.cos(Math.toRadians(num2));
		    				}
	    				}
	    			}if (calcPieces1[op1] == 't') {
	    				if (radDeg){
		    				if (num1 != 0) {
		    					work1 = num1 * Math.tan(num2);
		    				} else {
		    					work1 = Math.tan(num2);
		    				}
	    				} else {
	    					if (num1 != 0) {
		    					work1 = num1 * Math.tan(Math.toRadians(num2));
		    				} else {
		    					work1 = Math.tan(Math.toRadians(num2));
		    				}
	    				}
	    			}if (calcPieces1[op1] == 'i') {
	    				if (radDeg){
		    				if (num1 != 0) {
		    					work1 = num1 * Math.sin(num2);
		    				} else {
		    					work1 = Math.sin(num2);
		    				}
	    				} else {
	    					if (num1 != 0) {
		    					work1 = num1 * Math.sin(num2);
		    				} else {
		    					work1 = Math.sin(Math.toRadians(num2));
		    				}
	    				}
	    			}if (calcPieces1[op1] == 'a') {
		    			work1 = num1 + num2;
		    		}if (calcPieces1[op1] == 's') {
		    			work1 = num1 - num2;
		    		}if (calcPieces1[op1] == 'm') {
		    			work1 = num1 * num2;
		    		}if (calcPieces1[op1] == 'd') {
		    			work1 = num1 / num2;
		    		}
		    		endAnswer = work1;
	    		}
	    		op2IfState:
	    		if (ops == 2) {
	    			num1 = Double.parseDouble(calcs.substring(0, op1));
	    			num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    			num3 = Double.parseDouble(calcs.substring(op2+1, calcs.length()));
	    			if (calcPieces1[op1] == 'm' || calcPieces1[op1] == 'd') {
		    			if (calcPieces1[op1] == 'm') {
			    			work1 = num1 * num2;
			    		}if (calcPieces1[op1] == 'd') {
			    			work1 = num1 / num2;
			    		}
			    		if (calcPieces1[op2] == 'm' || calcPieces1[op2] == 'd') {
			    			if (calcPieces1[op2] == 'm') {
				    			work2 = work1 * num3;
				    		}if (calcPieces1[op2] == 'd') {
				    			work2 = work1 / num3;
				    		}
				    		endAnswer = work2;
				    		break op2IfState;
			    		}
			    		
			    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
			    			if (calcPieces1[op2] == 'a') {
				    			work2 = work1 + num3;
				    		}if (calcPieces1[op2] == 's') {
				    			work2 = work1 - num3;
				    		}
			    		}
		    		}
	    			
	    			if (calcPieces1[op2] == 'm' || calcPieces1[op2] == 'd') {
		    			if (calcPieces1[op2] == 'm') {
			    			work2 = num2 * num3;
			    		}if (calcPieces1[op2] == 'd') {
			    			work2 = num2 / num3;
			    		}
			    		if (calcPieces1[op1] == 'a' || calcPieces1[op1] == 's') {
			    			if (calcPieces1[op1] == 'a') {
				    			work1 = num1 + work2;
				    		}if (calcPieces1[op1] == 's') {
				    			work1 = num1 - work2;
				    		}
				    		endAnswer = work1;
				    		break op2IfState;
			    		}
		    		}
	    			if (calcPieces1[op1] == 's' || calcPieces1[op1] == 'a') {
		    			if (calcPieces1[op1] == 'a') {
			    			work1 = num1 + num2;
			    		}if (calcPieces1[op1] == 's') {
			    			work1 = num1 - num2;
			    		}
			    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
			    			if (calcPieces1[op2] == 'a') {
				    			work2 = work1 + num3;
				    		}if (calcPieces1[op2] == 's') {
				    			work2 = work1 - num3;
				    		}
			    		}
	    			}
		    		endAnswer = work2;
	    		}
	    		//All working :)
	    		op3IfState:
	    		if (ops == 3) {
	    			num1 = Double.parseDouble(calcs.substring(0, op1));
	    			num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    			num3 = Double.parseDouble(calcs.substring(op2+1, op3));
	    			num4 = Double.parseDouble(calcs.substring(op3+1, calcs.length()));
	    			
	    			if (calcPieces1[op1] == 'm' || calcPieces1[op1] == 'd') {
		    			if (calcPieces1[op1] == 'm') {
			    			work1 = num1 * num2;
			    		}if (calcPieces1[op1] == 'd') {
			    			work1 = num1 / num2;
			    		}
			    		if (calcPieces1[op3] == 'm' || calcPieces1[op3] == 'd') {
			    			if (calcPieces1[op3] == 'm') {
				    			work3 = num3 * num4;
				    		}if (calcPieces1[op3] == 'd') {
				    			work3 = num3 / num4;
				    		}
				    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
				    			if (calcPieces1[op2] == 'a') {
					    			work2 = work1 + work3;
					    		}if (calcPieces1[op2] == 's') {
					    			work2 = work1 - work3;
					    		}
				    		}
				    		endAnswer = work2;
				    		break op3IfState;
			    		}
			    		if (calcPieces1[op2] == 'm' || calcPieces1[op2] == 'd') {
			    			if (calcPieces1[op2] == 'm') {
				    			work2 = work1 * num3;
				    		}if (calcPieces1[op2] == 'd') {
				    			work2 = work1 / num3;
				    		}
				    		if (calcPieces1[op3] == 'm' || calcPieces1[op3] == 'd') {
				    			if (calcPieces1[op3] == 'm') {
					    			work3 = work2 * num4;
					    		}if (calcPieces1[op3] == 'd') {
					    			work3 = work2 / num4;
					    		}
				    		}
				    		if (calcPieces1[op3] == 'a' || calcPieces1[op3] == 's') {
				    			if (calcPieces1[op3] == 's') {
					    			work3 = work2 - num4;
					    		}if (calcPieces1[op3] == 'a') {
					    			work3 = work2 + num4;
					    		}
				    		}
			    		}
			    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
			    			if (calcPieces1[op2] == 'a') {
				    			work2 = work1 + num3;
				    		}if (calcPieces1[op2] == 's') {
				    			work2 = work1 - num3;
				    		}
				    		if (calcPieces1[op3] == 'm' || calcPieces1[op3] == 'd') {
				    			if (calcPieces1[op3] == 'm') {
					    			work3 = work2 * num4;
					    		}if (calcPieces1[op3] == 'd') {
					    			work3 = work2 / num4;
					    		}
				    		}
				    		if (calcPieces1[op3] == 'a' || calcPieces1[op3] == 's') {
				    			if (calcPieces1[op3] == 's') {
					    			work3 = work2 - num4;
					    		}if (calcPieces1[op3] == 'a') {
					    			work3 = work2 + num4;
					    		}
				    		}
			    		}
		    		}
	    			
	    			if (calcPieces1[op2] == 'm' || calcPieces1[op2] == 'd') {
		    			if (calcPieces1[op2] == 'm') {
			    			work2 = num2 * num3;
			    		}if (calcPieces1[op2] == 'd') {
			    			work2 = num2 / num3;
			    		}
			    		if (calcPieces1[op3] == 'm' || calcPieces1[op3] == 'd') {
			    			if (calcPieces1[op3] == 'm') {
				    			work3 = work2 * num4;
				    		}if (calcPieces1[op3] == 'd') {
				    			work3 = work2 / num4;
				    		}
				    		if (calcPieces1[op1] == 'a' || calcPieces1[op1] == 's') {
				    			if (calcPieces1[op1] == 'a') {
					    			work1 = num1 + work3;
					    		}if (calcPieces1[op1] == 's') {
					    			work1 = num1 - work3;
					    		}
				    		}
				    		endAnswer = work1;
				    		break op3IfState;
			    		} else {
			    		if (calcPieces1[op1] == 'a' || calcPieces1[op1] == 's') {
			    			if (calcPieces1[op1] == 'a') {
				    			work1 = num1 + work2;
				    		}if (calcPieces1[op1] == 's') {
				    			work1 = num1 - work2;
				    		}
				    		if (calcPieces1[op3] == 'a' || calcPieces1[op3] == 's') {
				    			if (calcPieces1[op3] == 'a') {
					    			work3 = work1 + num4;
					    		}if (calcPieces1[op3] == 's') {
					    			work3 = work1 - num4;
					    		}
				    		}
				    		endAnswer = work3;
				    		break op3IfState;
			    		}
			    		}
		    		}
	    			if (calcPieces1[op3] == 'm' || calcPieces1[op3] == 'd') {
		    			if (calcPieces1[op3] == 'd') {
			    			work3 = num3 / num4;
			    		}if (calcPieces1[op3] == 'm') {
			    			work3 = num3 * num4;
			    		}
			    		if (calcPieces1[op1] == 'a' || calcPieces1[op1] == 's') {
			    			if (calcPieces1[op1] == 'a') {
				    			work1 = num1 + num2;
				    		}if (calcPieces1[op1] == 's') {
				    			work1 = num1 - num2;
				    		}
				    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
				    			if (calcPieces1[op2] == 'a') {
					    			work2 = work1 + work3;
					    		}if (calcPieces1[op2] == 's') {
					    			work2 = work1 - work3;
					    		}
				    		}
				    		endAnswer = work2;
				    		break op3IfState;
			    		}
		    		}
	    			if (calcPieces1[op1] == 's' || calcPieces1[op1] == 'a') {
		    			if (calcPieces1[op1] == 'a') {
			    			work1 = num1 + num2;
			    		}if (calcPieces1[op1] == 's') {
			    			work1 = num1 - num2;
			    		}
			    		if (calcPieces1[op2] == 'a' || calcPieces1[op2] == 's') {
			    			if (calcPieces1[op2] == 'a') {
				    			work2 = work1 + num3;
				    		}if (calcPieces1[op2] == 's') {
				    			work2 = work1 - num3;
				    		}
				    		if (calcPieces1[op3] == 'a' || calcPieces1[op3] == 's') {
				    			if (calcPieces1[op3] == 'a') {
					    			work3 = work2 + num4;
					    		}if (calcPieces1[op3] == 's') {
					    			work3 = work2 - num4;
					    		}
				    		}
			    		}
	    			}
	    			
		    		endAnswer = work3;
	    		}
	    		if (ops == 4) {
	    			endAnswer = 0;
	    		}
	    		switch (decPlace) {
	    		case 3: DecimalFormat threeDForm = new DecimalFormat("#.###");
	    				endAnswer = Double.valueOf(threeDForm.format(endAnswer));
	    				break;
	    		case 4: DecimalFormat fourDForm = new DecimalFormat("#.####");
	    				endAnswer = Double.valueOf(fourDForm.format(endAnswer));
	    				break;
	    		case 5: DecimalFormat fiveDForm = new DecimalFormat("#.#####");
	    				endAnswer = Double.valueOf(fiveDForm.format(endAnswer));
	    				break;
	    		case 6: DecimalFormat sixDForm = new DecimalFormat("#.######");
	    				endAnswer = Double.valueOf(sixDForm.format(endAnswer));
	    				break;
	    		}
	    		calcs = Double.toString(endAnswer);
	    		shareString = calcString + " = " + endAnswer;
	    		display();
    		}
        }
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.arithmetic, container, false);
    		Button zeroButton = (Button) v.findViewById(R.id.zeroButton);
    		Button oneButton = (Button) v.findViewById(R.id.oneButton);
    		Button twoButton = (Button) v.findViewById(R.id.twoButton);
    		Button threeButton = (Button) v.findViewById(R.id.threeButton);
    		Button fourButton = (Button) v.findViewById(R.id.fourButton);
    		Button fiveButton = (Button) v.findViewById(R.id.fiveButton);
    		Button sixButton = (Button) v.findViewById(R.id.sixButton);
    		Button sevenButton = (Button) v.findViewById(R.id.sevenButton);
    		Button eightButton = (Button) v.findViewById(R.id.eightButton);
    		Button nineButton = (Button) v.findViewById(R.id.nineButton);
    		Button pointButton = (Button) v.findViewById(R.id.pointButton);
    		Button addButton = (Button) v.findViewById(R.id.addButton);
    		Button subtractButton = (Button) v.findViewById(R.id.subtractButton);
    		Button multiplyButton = (Button) v.findViewById(R.id.multiplyButton);
    		Button divideButton = (Button) v.findViewById(R.id.divideButton);
    		Button equalsButton = (Button) v.findViewById(R.id.equalsButton);
    		divideButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "d";
    				display();
    			}
    		});
    		multiplyButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "m";
    				display();
    			}
    		});
    		addButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "a";
    				display();
    			}
    		});
    		subtractButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "s";
    				display();
    			}
    		});
    		equalsButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calculate();
    			}
    		});
    		zeroButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "0";
    				display();
    			}
    		});
    		oneButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "1";
    				display();
    			}
    		});
    		twoButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "2";
    				display();
    			}
    		});
    		threeButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "3";
    				display();
    			}
    		});
    		fourButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "4";
    				display();
    			}
    		});
    		fiveButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "5";
    				display();
    			}
    		});
    		sixButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "6";
    				display();
    			}
    		});
    		sevenButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "7";
    				display();
    			}
    		});
    		eightButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "8";
    				display();
    			}
    		});
    		nineButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + "9";
    				display();
    			}
    		});
    		pointButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calcs = calcs + ".";
    				display();
    			}
    		});
    		return v;
    	}
    	
    	public void display() {
    		calcString = "";
    		char[] convertString = calcs.toCharArray();
			for (int i = 0; i < convertString.length; i++) {
				char current = convertString[i];
				if (current == '0') {
					calcString = calcString + "0";
				}
				if (current == '-') {
					calcString = calcString + "-";
				}
				if (current == '.') {
					calcString = calcString + ".";
				}
				if (current == '1') {
					calcString = calcString + "1";
				}
				if (current == '2') {
					calcString = calcString + "2";
				}
				if (current == '3') {
					calcString = calcString + "3";
				}
				if (current == '4') {
					calcString = calcString + "4";
				}
				if (current == '5') {
					calcString = calcString + "5";
				}
				if (current == '6') {
					calcString = calcString + "6";
				}
				if (current == '7') {
					calcString = calcString + "7";
				}
				if (current == '8') {
					calcString = calcString + "8";
				}
				if (current == '9') {
					calcString = calcString + "9";
				}
				if (current == 'a') {
					calcString = calcString + "+";
				}
				if (current == 's') {
					calcString = calcString + "-";
				}
				if (current == 'd') {
					calcString = calcString + "/";
				}
				if (current == 'm') {
					calcString = calcString + "x";
				}
				if (current == 'i') {
					calcString = calcString + "sin(";
				}
				if (current == 'c') {
					calcString = calcString + "cos(";
				}
				if (current == 't') {
					calcString = calcString + "tan(";
				}
				if (current == 'l') {
					calcString = calcString + "ln(";
				}
				if (current == 'o') {
					calcString = calcString + "log(";
				}
				if (current == 'f') {
					calcString = calcString + "!";
				}
				if (current == 'p') {
					calcString = calcString + "PI";
				}
				if (current == 'e') {
					calcString = calcString + "e";
				}
				if (current == 'u') {
					calcString = calcString + "^";
				}
				if (current == 'b') {
					calcString = calcString + ")";
				}
				if (current == 'r') {
					calcString = calcString + "^(.5)";
				}
			}
			TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
			calcView.setText(calcString);
    	}
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	getMenuInflater().inflate(R.menu.mainoptions, menu);
    	menu.findItem(R.id.help_menu_item).setIntent(new Intent(this, HelpActivity.class));
    	menu.findItem(R.id.settings_menu_item).setIntent(new Intent(this, SettingsActivity.class));
    	menu.findItem(R.id.copy_menu_item);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	if (item.getItemId() == R.id.settings_menu_item) {
    			startActivity(item.getIntent()); }
    	if (item.getItemId() == R.id.help_menu_item) {
			startActivity(item.getIntent()); }
    	if (item.getItemId() == R.id.copy_menu_item) {
    		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    		display();
    		ClipData clip = ClipData.newPlainText("text",calcString);
    		clipboard.setPrimaryClip(clip);
    		Toast.makeText(this, "Copied", 500).show();
    	}
    	return true;
    }
    public void PopUp(int title, int message,int icon){
        new AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setIcon(icon)
        .setPositiveButton("Close", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        }).show();
    }
    public void onShareClick(View v) {
    	Context mContext = getApplicationContext();
    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    	View layout = inflater.inflate(R.layout.share_dialog, (ViewGroup) findViewById(R.id.layout_root));
    	
    	TextView shareText = (TextView) layout.findViewById(R.id.ShareText);
    	shareText.setText(shareString);
    	
    	Spinner preTextSpinner = (Spinner) layout.findViewById(R.id.PreText_Spinner);
        ArrayAdapter<CharSequence> preTextAdapter = ArrayAdapter.createFromResource(this, R.array.pre_text, android.R.layout.simple_spinner_item);
        preTextAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preTextSpinner.setAdapter(preTextAdapter);
    	class PreTextItemSelectedListener implements OnItemSelectedListener {
    	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    	    	preTextChoice = pos;
    	    }
			public void onNothingSelected(AdapterView<?> arg0) {
			}
    	}
    	preTextSpinner.getPrompt();
    	preTextSpinner.setOnItemSelectedListener(new PreTextItemSelectedListener());
    	
    	Spinner networkSpinner = (Spinner) layout.findViewById(R.id.Network_Spinner);
        ArrayAdapter<CharSequence> netAdapter = ArrayAdapter.createFromResource(this, R.array.network, android.R.layout.simple_spinner_item);
        netAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        networkSpinner.setAdapter(netAdapter);
    	class NetworkItemSelectedListener implements OnItemSelectedListener {
    	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    	      networkChoice = pos;
    	    }
			public void onNothingSelected(AdapterView<?> arg0) {
			}
    	}
    	networkSpinner.getPrompt();
    	networkSpinner.setOnItemSelectedListener(new NetworkItemSelectedListener());
		
		new AlertDialog.Builder(this)
        .setTitle(R.string.share)
        .setIcon(R.drawable.share)
        .setView(layout)
        .setPositiveButton("Share", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            	shareIt(preTextChoice, networkChoice);
            	
            	Toast.makeText(getApplicationContext(), "Not shared", 1000).show();
            	
            }
        }).setNegativeButton("Nevermind", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
			}
        }).show();
		
    }
    
    public void onDeleteClick(View v) {
    	if (calcs.length() > 1) {
    		if(!calcs.equals("")) {
    			calcs = calcs.substring(0, calcs.length()-1);
    		}
    	} else {
    		calcs = "";
    	}
    	display();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
        SharedPreferences calcsPref = getSharedPreferences(CALCS, MODE_PRIVATE);
        SharedPreferences.Editor editor = calcsPref.edit();
        editor.putString("calcs", calcs);
        editor.commit();
    }
    
    public void changeMoreButton(int id) {
    	Button moreButton = (Button) findViewById(R.id.moreButton);
    	if (id == 1) {
    		moreButton.setText("MORE");
    	}
    	if (id == 2) {
    		moreButton.setText("BACK");
    	}
    }
    
    public void display() {
		calcString = "";
		char[] convertString = calcs.toCharArray();
		for (int i = 0; i < convertString.length; i++) {
			char current = convertString[i];
			if (current == '0') {
				calcString = calcString + "0";
			}
			if (current == '.') {
				calcString = calcString + ".";
			}
			if (current == '1') {
				calcString = calcString + "1";
			}
			if (current == '2') {
				calcString = calcString + "2";
			}
			if (current == '3') {
				calcString = calcString + "3";
			}
			if (current == '4') {
				calcString = calcString + "4";
			}
			if (current == '5') {
				calcString = calcString + "5";
			}
			if (current == '6') {
				calcString = calcString + "6";
			}
			if (current == '-') {
				calcString = calcString + "-";
			}
			if (current == '7') {
				calcString = calcString + "7";
			}
			if (current == '8') {
				calcString = calcString + "8";
			}
			if (current == '9') {
				calcString = calcString + "9";
			}
			if (current == 'a') {
				calcString = calcString + "+";
			}
			if (current == 's') {
				calcString = calcString + "-";
			}
			if (current == 'd') {
				calcString = calcString + "/";
			}
			if (current == 'm') {
				calcString = calcString + "x";
			}
			if (current == 'i') {
				calcString = calcString + "sin(";
			}
			if (current == 'c') {
				calcString = calcString + "cos(";
			}
			if (current == 't') {
				calcString = calcString + "tan(";
			}
			if (current == 'l') {
				calcString = calcString + "ln(";
			}
			if (current == 'o') {
				calcString = calcString + "log(";
			}
			if (current == 'f') {
				calcString = calcString + "!";
			}
			if (current == 'p') {
				calcString = calcString + "PI";
			}
			if (current == 'e') {
				calcString = calcString + "e";
			}
			if (current == 'u') {
				calcString = calcString + "^";
			}
			if (current == 'b') {
				calcString = calcString + ")";
			}
			if (current == 'r') {
				calcString = calcString + "^(.5)";
			}
		}
		TextView calcView = (TextView) findViewById(R.id.calc_bar);
		calcView.setText(calcString);
	}
    
    public void shareIt(int pre, int network) {
    	preTextChoice = pre;
    	networkChoice = network;
    	twitter = new Twitter(this, consumerKey, consumerSecret, callbackURL);
    	twitter.showAuthorization().setOnDismissListener(this);
    
    }

	public void onDismiss(DialogInterface dialog) {
		
	}
 }