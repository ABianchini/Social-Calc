package com.advancementbureau.socialcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArithmeticFragment extends Fragment{
	
	//OnButtonSelectedListener mListener;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Button zeroButton = (Button) getView().findViewById(R.id.buttonZero);
		zeroButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			}
		});*/
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.arithmetic, container, false);
		return view;
	}
	
	/*public void onZeroClick(View v) {
		if (v.getId() == R.id.buttonZero) {
			Button zeroButton = (Button) getView().findViewById(R.id.buttonZero);
			zeroButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
				}
			});
		}
	}*/
}