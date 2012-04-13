package com.advancementbureau.socialcalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SocialCalcActivity extends Activity {
	
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
        
    }

    public static class ArithmeticFragment extends Fragment {
    	
    	public void calculate() {
        	TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    		double tempCalc;
    		if (operation == 1) {
    			tempCalc = addOp + Double.parseDouble(calcString);
    			shareString = Double.toString(addOp) + " + "+ Double.parseDouble(calcString) + " = " + tempCalc;
    			calcString = Double.toString(tempCalc);
    		}
    		if (operation == 2) {
    			tempCalc = subOp - Double.parseDouble(calcString);
    			shareString = Double.toString(subOp) + " - "+ Double.parseDouble(calcString) + " = " + tempCalc;
    			calcString = Double.toString(tempCalc);
    		}
    		if (operation == 3) {
    			tempCalc = multOp * Double.parseDouble(calcString);
    			shareString = Double.toString(multOp) + " x "+ Double.parseDouble(calcString) + " = " + tempCalc;
    			calcString = Double.toString(tempCalc);
    		}
    		if (operation == 4) {
    			tempCalc = divOp / Double.parseDouble(calcString);
    			shareString = Double.toString(divOp) + " / "+ Double.parseDouble(calcString) + " = " + tempCalc;
    			calcString = Double.toString(tempCalc);
    		}
    		calcView.setText(calcString);
    		storeCalc(calcString);
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
    				divOp = x;
    				calcString = "";
    				calcView.setText(calcString);
    				operation = 4;
    			}
    		});
    		multiplyButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				multOp = x;
    				calcString = "";
    				calcView.setText(calcString);
    				operation = 3;
    			}
    		});
    		addButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				//calculate();
    				addOp = x;
    				calcString = "";
    				calcView.setText(calcString);
    				operation = 1;
    			}
    		});
    		subtractButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				subOp = x;
    				calcString = "";
    				calcView.setText(calcString);
    				operation = 2;
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
    				storeCalc(calcString);
    			}
    		});
    		oneButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "1";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		twoButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "2";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		threeButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "3";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		fourButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "4";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		fiveButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "5";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		sixButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "6";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		sevenButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "7";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		eightButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "8";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		nineButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + "9";
    				calcView.setText(calcString);
    				storeCalc(calcString);
    			}
    		});
    		pointButton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
    				calcString = calcView.getText().toString() + ".";
    				calcView.setText(calcString);
    				//storeCalc(calcString);
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
    	if (!calcString.equals("")) {
    		calcString = calcString.substring(0, calcString.length()-1);
    		storeCalc(calcString);
    	}
    	calcView.setText(calcString);
    }
    public void onMoreClick(View v) {
    	Toast.makeText(this, "Not yet...", 1000).show();
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	TextView calcView = (TextView) findViewById(R.id.calc_bar);
    	calcString = "";
		calcView.setText(calcString);
		storeCalc("0");
    }
    
 }