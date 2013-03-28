package com.example.newsapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity {

	public Intent i;
	
	
	public void goToNextActivity(){
		i = new Intent(this, MainActivity.class);
		startActivity(i);
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	      public void run() {
	    	  endActivity();	    	  
	      }
	    }, 2000); 
	}
	
	public void endActivity(){
		finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		SharedPreferences shared = getSharedPreferences("agree_terms_diglr_tech_news", MODE_PRIVATE);
		boolean agreed = shared.getBoolean("agreed", false);
		if(!agreed) {
			 final Handler handler = new Handler();
			 i = new Intent(this,PrivacyActivity.class);
			 handler.postDelayed(new Runnable() {
				 public void run() {
					 startActivity(i);
					 endActivity();	    	  
				 }
			 }, 400);   	
			    
			 return;
		}

		goToNextActivity();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
