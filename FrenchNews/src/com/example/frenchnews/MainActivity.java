package com.example.frenchnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.actionbarsherlock.view.Menu;



public class MainActivity extends SherlockFragmentActivity {

	TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
	
    private static final String[] CONTENT = new String[] { "Latest", "Politics", "Economy", "Sports", "Local" };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tabs);
		
		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
		
	}
	
	class TestFragmentAdapter extends FragmentPagerAdapter {
		
		private int mCount = CONTENT.length;
		
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
	    	return CONTENT[position];
	    }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSherlock().getMenuInflater().inflate(R.menu.activity_itemlist, menu);
		return true;
	}

}
