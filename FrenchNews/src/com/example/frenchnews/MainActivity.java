package com.example.frenchnews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.example.json.JSONParser;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
import com.viewpagerindicator.TitlePageIndicator.OnCenterItemClickListener;




public class MainActivity extends SherlockFragmentActivity implements OnCenterItemClickListener {

	TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
	
    private static String url = "http://10.0.2.2/getTabs.php";
    private static final String TAG_TABS = "tabs";
    private static final String TAG_VERSION = "version";
   //private static final String TAG_TAB = "tab";
    
    private SharedPreferences tabsPrefs;
    
    private JSONObject tabsJson;
    private int tabsVersion;
    private int tabsServerVersion;
    private JSONParser jParser;
    private JSONArray tabsJSON = null;
    private String[] tabsArray;
    
    String tabs;
    

	private void checkTabs() {
		tabsPrefs = getSharedPreferences("tabs", Activity.MODE_PRIVATE);
		tabs = tabsPrefs.getString("tabs", null);
		tabsVersion = tabsPrefs.getInt("tabsVersion", 0);
		
		jParser = new JSONParser();
		tabsJson = jParser.getJSONFromUrl(url);
        
		if(tabsUpdated(tabsVersion) || tabs == null || tabs == "") {
			updateTabs();
		}
		
		tabsArray = tabs.split("#");
	}
	
    private boolean tabsUpdated(int currentVersion) {
    	try {
        	tabsServerVersion = tabsJson.getInt(TAG_VERSION);
        	Log.d("Tabs Server Version","x" + tabsServerVersion);
        	if(tabsServerVersion > currentVersion) {
        		return true;
        	} else {
        		return false;
        	}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    private void updateTabs() {
        try {
        	tabsJSON = tabsJson.getJSONArray(TAG_TABS);
        	tabs = new String();
            for(int i = 0; i < tabsJSON.length(); i++){
            	tabs += tabsJSON.getString(i);
            	if(i < tabsJSON.length() -1) {
            		tabs += "#";
            	}
            }
            
            Editor editor = tabsPrefs.edit();
            editor.putString("tabs", tabs);
            editor.putInt("tabsVersion", tabsJson.getInt(TAG_VERSION));
            editor.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_titles);
		
		checkTabs();
		
		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
        indicator.setOnCenterItemClickListener(this);
        mIndicator = indicator;
		
	}
	
	
	class TestFragmentAdapter extends FragmentPagerAdapter {
		
		private int mCount = tabsArray.length;
		
		public TestFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return TestFragment.newInstance(String.valueOf(position));
		}

		@Override
		public int getCount() {
			return mCount;
		}
		
		@Override
	    public CharSequence getPageTitle(int position) {
			//Log.d("Tab title", tabsArray[position]);
	    	return tabsArray[position];
	    }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSherlock().getMenuInflater().inflate(R.menu.activity_itemlist, menu);
		return true;
	}

	@Override
	public void onCenterItemClick(int position) {
		Toast.makeText(this, "You clicked the center title!", Toast.LENGTH_SHORT).show();
	}

}
