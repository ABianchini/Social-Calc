package com.advancementbureau.socialcalc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArithmeticFragment extends Fragment {
	
	//TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
	OnButtonSelectedListener mListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
	
	public interface OnButtonSelectedListener {
		public void onButtonSelected(int id);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnButtonSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + "must implement OnButtonSelectedListener");
		}
	}
	
	public void onZeroClick(View v) {
		//mListener.onButtonSelected(0);
		//SocialCalcActivity.calcBar = calcView.getText().toString();
		//SocialCalcActivity.calcBar = SocialCalcActivity.calcBar + "0";
		//calcView.setText(SocialCalcActivity.calcBar);
	}
}