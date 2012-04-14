package com.advancementbureau.socialcalc;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SocialCalcActivity extends Activity {
	
	public static final String GAME_PREFERENCES = "GamePrefs";
	public static final String CALCS = "calcs";
	public static final String CALCSTRING = "show";
	SharedPreferences mGameSettings;
	public static String calcString = "";
	public static double x;
	public static double y;
	public static double z;
	public static String shareString;
	public static String calcs = "";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView calcView = (TextView) findViewById(R.id.calc_bar);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
        	public boolean onLongClick(View view) {
        		calcString = "";
        		calcView.setText(calcString);
        		calcs = "";
        		return true;
        	}
        });
        mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        if (mGameSettings.contains(CALCS)) {
			calcs = mGameSettings.getString(CALCS, "");
		}
        if (mGameSettings.contains(CALCSTRING)) {
			calcString = mGameSettings.getString(CALCSTRING, "");
		}
        calcView.setText(calcString);
        
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
    
    public static class AdvancedFragment extends Fragment {
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		View v = inflater.inflate(R.layout.advanced, container, false);
    		
    		Button sinButton = (Button) v.findViewById(R.id.sinButton);
    		Button cosButton = (Button) v.findViewById(R.id.sinButton);
    		Button tanButton = (Button) v.findViewById(R.id.sinButton);
    		Button lnButton = (Button) v.findViewById(R.id.sinButton);
    		Button logButton = (Button) v.findViewById(R.id.sinButton);
    		Button factButton = (Button) v.findViewById(R.id.sinButton);
    		Button piButton = (Button) v.findViewById(R.id.sinButton);
    		Button eButton = (Button) v.findViewById(R.id.sinButton);
    		Button powerButton = (Button) v.findViewById(R.id.sinButton);
    		
    		return v;
    	}
    }

    public static class ArithmeticFragment extends Fragment {
    	
    	public void calculate() {
        	TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    		double work1 = 0;
    		double work2 = 0;
    		double work3 = 0;
    		double work4 = 0;
    		double num1 = 1;
    		double num2 = 1;
    		double num3 = 1;
    		double num4 = 1;
    		double num5 = 1;
    		int ops = 0;
    		int op1 = 13;
    		int op2 = 13;
    		int op3 = 13;
    		int op4 = 13;
    		double endAnswer = 0;
    		
    		if(!calcs.equals("")) {
    			char[] calcPieces = calcs.toCharArray();
    			for (int i = 0; i < calcPieces.length; i++) {
    				char current = calcPieces[i];
    				if (current == 'a' || current == 's' || current == 'm' || current == 'd') {
    					ops++;
    				}
    			}
    		}
    		
    		if (!calcs.equals("")) {
	    		char[] calcPieces1 = calcs.toCharArray();
	    		calcLoop1:
	    		for (int i = 0; i < calcPieces1.length; i++) {
	    			char current = calcPieces1[i];
	    			if (current == 'a' || current == 's' || current == 'm' || current == 'd') {
	    				op1 = i;
	    				num1 = Double.parseDouble(calcs.substring(0, op1));
	    				
	    				for (int j = op1+1; j < calcPieces1.length; j++) {
	    					char current2 = calcPieces1[j];
	    					if (current2 == 'a' || current2 == 's' || current2 == 'm' || current2 == 'd') {
	    						op2 = j;
	    						num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    						if (ops == 2) break calcLoop1;
	    						
	    						for (int k = op2+1; k < calcPieces1.length; k++) {
	    	    					char current3 = calcPieces1[k];
	    	    					if (current3 == 'a' || current3 == 's' || current3 == 'm' || current3 == 'd') {
	    	    						op3 = k;
	    	    						num3 = Double.parseDouble(calcs.substring(op2+1, op3));
	    	    						if (ops == 3) break calcLoop1;
	    	    						
	    	    						for (int l = op3+1; l < calcPieces1.length; l++) {
	    	    	    					char current4 = calcPieces1[l];
	    	    	    					if (current4 == 'a' || current4 == 's' || current4 == 'm' || current4 == 'd') {
	    	    	    						op4 = l;
	    	    	    						num4 = Double.parseDouble(calcs.substring(op3+1, op4));
	    	    	    						if (ops == 4) break calcLoop1;
	    	    	    					}
	    	    	    				}
	    	    					}
	    	    				}
	    					}
	    				}
	    			}
	    		}
	    		if (ops == 1) {
	    			num2 = Double.parseDouble(calcs.substring(op1+1, calcs.length()));
	    			if (calcPieces1[op1] == 'a') {
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
	    		if (ops == 2) {
	    			num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    			if (calcPieces1[op1] == 'a') {
		    			work1 = num1 + num2;
		    		}if (calcPieces1[op1] == 's') {
		    			work1 = num1 - num2;
		    		}if (calcPieces1[op1] == 'm') {
		    			work1 = num1 * num2;
		    		}if (calcPieces1[op1] == 'd') {
		    			work1 = num1 / num2;
		    		}
		    		endAnswer = work1;
	    			num3 = Double.parseDouble(calcs.substring(op2+1, calcs.length()));
	    			if (calcPieces1[op2] == 'a') {
		    			work2 = work1 + num3;
		    		}if (calcPieces1[op2] == 's') {
		    			work2 = work1 - num3;
		    		}if (calcPieces1[op2] == 'm') {
		    			work2 = work1 * num3;
		    		}if (calcPieces1[op2] == 'd') {
		    			work2 = work1 / num3;
		    		}
		    		endAnswer = work2;
	    		}
	    		if (ops == 3) {
	    			num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    			if (calcPieces1[op1] == 'a') {
		    			work1 = num1 + num2;
		    		}if (calcPieces1[op1] == 's') {
		    			work1 = num1 - num2;
		    		}if (calcPieces1[op1] == 'm') {
		    			work1 = num1 * num2;
		    		}if (calcPieces1[op1] == 'd') {
		    			work1 = num1 / num2;
		    		}
		    		endAnswer = work1;
	    			num3 = Double.parseDouble(calcs.substring(op2+1, op3));
	    			if (calcPieces1[op2] == 'a') {
		    			work2 = work1 + num3;
		    		}if (calcPieces1[op2] == 's') {
		    			work2 = work1 - num3;
		    		}if (calcPieces1[op2] == 'm') {
		    			work2 = work1 * num3;
		    		}if (calcPieces1[op2] == 'd') {
		    			work2 = work1 / num3;
		    		}
		    		endAnswer = work2;
	    			num4 = Double.parseDouble(calcs.substring(op3+1, calcs.length()));
	    			if (calcPieces1[op3] == 'a') {
		    			work3 = work2 + num4;
		    		}if (calcPieces1[op3] == 's') {
		    			work3 = work2 - num4;
		    		}if (calcPieces1[op3] == 'm') {
		    			work3 = work2 * num4;
		    		}if (calcPieces1[op3] == 'd') {
		    			work3 = work2 / num4;
		    		}
		    		endAnswer = work3;
	    		}
	    		if (ops == 4) {
	    			num2 = Double.parseDouble(calcs.substring(op1+1, op2));
	    			if (calcPieces1[op1] == 'a') {
		    			work1 = num1 + num2;
		    		}if (calcPieces1[op1] == 's') {
		    			work1 = num1 - num2;
		    		}if (calcPieces1[op1] == 'm') {
		    			work1 = num1 * num2;
		    		}if (calcPieces1[op1] == 'd') {
		    			work1 = num1 / num2;
		    		}
		    		endAnswer = work1;
	    			num3 = Double.parseDouble(calcs.substring(op2+1, op3));
	    			if (calcPieces1[op2] == 'a') {
		    			work2 = work1 + num3;
		    		}if (calcPieces1[op2] == 's') {
		    			work2 = work1 - num3;
		    		}if (calcPieces1[op2] == 'm') {
		    			work2 = work1 * num3;
		    		}if (calcPieces1[op2] == 'd') {
		    			work2 = work1 / num3;
		    		}
		    		endAnswer = work2;
	    			num4 = Double.parseDouble(calcs.substring(op3+1, op4));
	    			if (calcPieces1[op3] == 'a') {
		    			work3 = work2 + num4;
		    		}if (calcPieces1[op3] == 's') {
		    			work3 = work2 - num4;
		    		}if (calcPieces1[op3] == 'm') {
		    			work3 = work2 * num4;
		    		}if (calcPieces1[op3] == 'd') {
		    			work3 = work2 / num4;
		    		}
		    		num5 = Double.parseDouble(calcs.substring(op4+1, calcs.length()));
	    			if (calcPieces1[op4] == 'a') {
		    			work4 = work3 + num5;
		    		}if (calcPieces1[op4] == 's') {
		    			work4 = work3 - num5;
		    		}if (calcPieces1[op4] == 'm') {
		    			work4 = work3 * num5;
		    		}if (calcPieces1[op4] == 'd') {
		    			work4 = work3 / num5;
		    		}
		    		endAnswer = work4;
	    		}
	    		DecimalFormat fourDForm = new DecimalFormat("#.#####");
	    		endAnswer = Double.valueOf(fourDForm.format(endAnswer));
	    		shareString = calcString + " = " + endAnswer;
	    		calcString = Double.toString(endAnswer);
	    		calcs = Double.toString(endAnswer);
	    		calcView.setText(calcString);
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
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "/";
    				calcView.setText(calcString);
    				calcs = calcs + "d";
    			}
    		});
    		multiplyButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "x";
    				calcView.setText(calcString);
    				calcs = calcs + "m";
    			}
    		});
    		addButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "+";
    				calcView.setText(calcString);
    				calcs = calcs + "a";
    			}
    		});
    		subtractButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "-";
    				calcView.setText(calcString);
    				calcs = calcs + "s";
    			}
    		});
    		equalsButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				calculate();
    			}
    		});
    		zeroButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "0";
    				calcView.setText(calcString);
    				calcs = calcs + "0";
    			}
    		});
    		oneButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "1";
    				calcView.setText(calcString);
    				calcs = calcs + "1";
    			}
    		});
    		twoButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "2";
    				calcView.setText(calcString);
    				calcs = calcs + "2";
    			}
    		});
    		threeButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "3";
    				calcView.setText(calcString);
    				calcs = calcs + "3";
    				//calcView.setText(calcs);
    			}
    		});
    		fourButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "4";
    				calcView.setText(calcString);
    				calcs = calcs + "4";
    			}
    		});
    		fiveButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "5";
    				calcView.setText(calcString);
    				calcs = calcs + "5";
    			}
    		});
    		sixButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "6";
    				calcView.setText(calcString);
    				calcs = calcs + "6";
    			}
    		});
    		sevenButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "7";
    				calcView.setText(calcString);
    				calcs = calcs + "7";
    			}
    		});
    		eightButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "8";
    				calcView.setText(calcString);
    				calcs = calcs + "8";
    			}
    		});
    		nineButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "9";
    				calcView.setText(calcString);
    				calcs = calcs + "9";
    			}
    		});
    		pointButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + ".";
    				calcView.setText(calcString);
    				calcs = calcs + ".";
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
    	//PopUp(R.string.share, R.string.share_info, R.drawable.share);
    	new AlertDialog.Builder(this)
        .setTitle(R.string.share)
        .setMessage(shareString)
        .setIcon(R.drawable.share)
        .setPositiveButton("Close", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        }).show();
    }
    public void onDeleteClick(View v) {
    	TextView calcView = (TextView) findViewById(R.id.calc_bar);
    	calcString = calcView.getText().toString();
    	if (calcString.length() > 1) {
	    	if (!calcString.equals("")) {
	    		calcString = calcString.substring(0, calcString.length()-1);
	    		calcs = calcs.substring(0, calcs.length()-1);
	    	}
    	} else {
    		calcString = "";
    		calcs = "";
    	}
    	calcView.setText(calcString);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
        SharedPreferences calcsPref = getSharedPreferences(CALCS, MODE_PRIVATE);
        SharedPreferences.Editor editor = calcsPref.edit();
        editor.putString("calcs", calcs);
        editor.commit();
        SharedPreferences calcStringPref = getSharedPreferences(CALCSTRING, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = calcStringPref.edit();
        editor2.putString("show", calcString);
        editor2.commit();
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
    
 }