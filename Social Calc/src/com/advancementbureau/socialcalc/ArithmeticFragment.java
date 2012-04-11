package com.advancementbureau.socialcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArithmeticFragment extends Fragment {
	
	TextView calcView = (TextView) getActivity().findViewById(R.id.calc_bar);
	
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
	public void onZeroClick(View v) {
		SocialCalcActivity.calcBar = calcView.getText().toString();
		SocialCalcActivity.calcBar = SocialCalcActivity.calcBar + "0";
		calcView.setText(SocialCalcActivity.calcBar);
		//SocialCalcActivity.PopUp(R.string.zero, R.string.eight);
	}
}