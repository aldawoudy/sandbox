package com.example.frenchnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentOne extends SherlockFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragmentone, container, false);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		String text = getArguments().getString("tab");
		tv.setText(text);
		
		
		return view;
	}
	
}
