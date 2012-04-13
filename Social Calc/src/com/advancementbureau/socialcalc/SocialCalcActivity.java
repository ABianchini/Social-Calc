package com.advancementbureau.socialcalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
	
	public static final String GAME_PREFERENCES = "GamePrefs";
	public static final String CALCS = "boot";
	SharedPreferences mGameSettings;
	public static String calcString = "";
	public static double x;
	public static double y;
	public static double z;
	public static int operation = 0;
	public static double addOp;
	public static double subOp;
	public static double multOp;
	public static double divOp;
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
        		storeCalc("0");
        		return true;
        	}
        });
        
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
    		return v;
    	}
    }

    public static class ArithmeticFragment extends Fragment {
    	//TODO A TON OF STUFF!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	public void calculate() {
        	//TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    		double work1;
    		double work2;
    		double work3;
    		double work4;
    		double work5;
    		double num1 = 1;
    		double num2 = 1;
    		double num3 = 1;
    		double num4 = 1;
    		double num5 = 1;
    		int op1 = 13;
    		int op2 = 13;
    		int op3 = 13;
    		int op4 = 13;
    		int op5 = 13;
    		char[] calcPieces = calcs.toCharArray();
    		for (int i = 0; i < calcPieces.length; i++) {
    			char current = calcPieces[i];
    			if (!Character.isDigit(current)) {
    				op1 = i;
    				num1 = Integer.parseInt(calcs.substring(0, op1+1));
    				for (int j = op1; j < calcPieces.length; j++) {
    					char current2 = calcPieces[j];
    					if (!Character.isDigit(current2)) {
    						op2 = j;
    						num2 = Integer.parseInt(calcs.substring(op1+1, op2 +1));
    						for (int k = op1; k < calcPieces.length; k++) {
    	    					char current3 = calcPieces[k];
    	    					if (!Character.isDigit(current3)) {
    	    						op3 = k;
    	    						num3 = Integer.parseInt(calcs.substring(op2+1, op3 +1));
    	    						for (int l = op1; l < calcPieces.length; l++) {
    	    	    					char current4 = calcPieces[l];
    	    	    					if (!Character.isDigit(current4)) {
    	    	    						op4 = l;
    	    	    						num4 = Integer.parseInt(calcs.substring(op3+1, op4+1));
    	    	    						for (int m = op1; m < calcPieces.length; m++) {
    	    	    	    					char current5 = calcPieces[m];
    	    	    	    					if (!Character.isDigit(current5)) {
    	    	    	    						op5 = m;
    	    	    	    						num5 = Integer.parseInt(calcs.substring(op4+1, op5+1));
    	    	    	    					}
    	    	    	    				}
    	    	    					}
    	    	    				}
    	    					}
    	    				}
    					}
    				}
    			}
    		}
    		if (op1 != 13) {
	    		if (calcPieces[op1] == 'a') {
	    			work1 = num1 + num2;
	    		}
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
    
    public static void storeCalc(String input) {
    	z = y;
    	y = x;
    	x = Double.parseDouble(input);
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
	    		storeCalc(calcString);
	    	}
    	} else {
    		calcString = "";
    	}
    	calcView.setText(calcString);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	TextView calcView = (TextView) findViewById(R.id.calc_bar);
    	calcString = "";
		calcView.setText(calcString);
		mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences bootPref = getSharedPreferences(CALCS, MODE_PRIVATE);
        SharedPreferences.Editor editor = bootPref.edit();
        editor.putString("boot", calcs);
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
    
 }