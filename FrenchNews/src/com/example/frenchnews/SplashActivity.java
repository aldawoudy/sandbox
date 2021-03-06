package com.example.frenchnews;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;



public class SplashActivity extends SherlockFragmentActivity {

	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		
		setContentView(mViewPager);
		
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mTabsAdapter = new TabsAdapter(this, mViewPager);
		
		Bundle argsSocial = new Bundle();
		Bundle argsProfile = new Bundle();
		Bundle argsGroup = new Bundle();
		Bundle argsMap = new Bundle();
		Bundle argsSettings = new Bundle();
		argsSocial.putString("tab", "Social Tab");
		argsProfile.putString("tab", "Profile Tab");
		argsGroup.putString("tab", "Group Tab");
		argsMap.putString("tab", "Map Tab");
		argsSettings.putString("tab", "Settings Tab");
		String[] tabNames = {"Social Tab", "Profile Tab", "Group Tab", "Map Tab", "Settings Tab"};
		
		Bundle args = new Bundle();
		
		args.putString("tab", tabNames[mViewPager.getCurrentItem()]);
		
		mTabsAdapter.addTab(bar.newTab().setText("Social").setIcon(R.drawable.social_chat), FragmentOne.class, argsSocial);
		mTabsAdapter.addTab(bar.newTab().setText("Profile").setIcon(R.drawable.social_person), FragmentOne.class, argsProfile);
		mTabsAdapter.addTab(bar.newTab().setText("Groups").setIcon(R.drawable.social_group), FragmentOne.class, argsGroup);
		mTabsAdapter.addTab(bar.newTab().setText("Map").setIcon(R.drawable.location_map), FragmentOne.class, argsMap);
		mTabsAdapter.addTab(bar.newTab().setText("Social").setIcon(R.drawable.action_settings), FragmentOne.class, argsSettings);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSherlock().getMenuInflater().inflate(R.menu.activity_itemlist, menu);
		return true;
	}
	
	public static class TabsAdapter extends FragmentPagerAdapter implements
	ActionBar.TabListener, ViewPager.OnPageChangeListener
	{

		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
		
		static final class TabInfo
		{
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args)
			{
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getSupportActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}
		
		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args)
		{
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft)
		{
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++)
			{
				if (mTabs.get(i) == tag)
				{
					mViewPager.setCurrentItem(i);
				}
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(), info.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}
			
	}
	

}
