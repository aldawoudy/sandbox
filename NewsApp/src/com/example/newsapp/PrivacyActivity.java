package com.example.newsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class PrivacyActivity extends Activity {

	public Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privacy);
		WebView activeWebview  = (WebView) findViewById(R.id.privacyView);			
		// activeWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);		
		activeWebview.loadUrl( "file:///android_asset/www/privacy_policy.html");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.privacy, menu);
		return true;
	}
	

	public void _disagreeTerms(View v){
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
    	alt_bld
    	.setMessage("Are you sure you disagree to the terms and want to exit ?")    	    	
    	.setCancelable(false)
    	.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			//  Action for 'NO' Button
    			dialog.cancel();    			
    		}
    	})
    	.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			//  Action for 'NO' Button
    			dialog.cancel();
    			finish();
	            System.exit(0);
    		}
    	});
    	
    	
    	AlertDialog alert = alt_bld.create();
    	// Title for AlertDialog
    	alert.setTitle("Privacy Policy Declined!");
    	alert.show();
	}
	
	public void _acceptTerms(View v){
		SharedPreferences shared = getSharedPreferences("agree_terms_diglr_tech_news", MODE_PRIVATE);
		shared.edit().putBoolean("agreed", true).commit();	
		
		// save the settings of agreement
		i = new Intent( PrivacyActivity.this, MainActivity.class );                       
        startActivity(i);        
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	      public void run() {
	    	  endActivity();	    	  
	      }
	    }, 400);
	}
	
	
	public void endActivity(){
		finish();
	}

}
