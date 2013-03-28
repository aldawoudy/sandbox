package com.example.newsapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
 
public class TabsFragment extends Fragment implements OnTabChangeListener {
 
    private static final String TAG = "FragmentTabs";
    public static final String TAB_WORDS = "words";
    public static final String TAB_NUMBERS = "numbers";
    
    public static final String TAB_LATEST = "latest";
    public static final String TAB_POLITICS = "politics";
    public static final String TAB_SPORTS = "sports";
    public static final String TAB_ECONOMICS = "economics";
    
 // url to make request
    private static String url = "http://10.0.2.2/getNews.php";
    private String currentCat; 
    // JSON Node names
    private static final String TAG_NEWS = "news";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_DATE = "date";
    private static final String TAG_CAT = "cat";

     
    // contacts JSONArray
    private JSONArray newsItems = null;
    
    ArrayList<HashMap<String, String>> newsList;
    
    JSONParser jParser;

 
    private View mRoot;
    private TabHost mTabHost;
    private int mCurrentTab;
    private String[] mCurrentValues;
 
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.tabs_fragment, null);
        mTabHost = (TabHost) mRoot.findViewById(android.R.id.tabhost);
        setupTabs();
        return mRoot;
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
 
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(mCurrentTab);
        // manually start loading stuff in the first tab
        mCurrentValues = new String[] { "Android", "iPhone", "WindowsMobile" };
        currentCat = TAB_LATEST;
        updateCurrentNews();
        updateTab(TAB_LATEST, R.id.tab_1);
        
    }
    
    private void updateCurrentNews() {
    	// Hashmap for ListView
        newsList = new ArrayList<HashMap<String, String>>();
 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url+"?c="+currentCat);
 
        try {
            // Getting Array of Contacts
        	newsItems = json.getJSONArray(TAG_NEWS);
 
            // looping through All Contacts
            for(int i = 0; i < newsItems.length(); i++){
                JSONObject c = newsItems.getJSONObject(i);
 
                // Storing each json item in variable
                String id = c.getString(TAG_ID);
                String title = c.getString(TAG_TITLE);
                String content = c.getString(TAG_CONTENT);
                String date = c.getString(TAG_DATE);
                String cat = c.getString(TAG_CAT);

                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_ID, id);
                map.put(TAG_TITLE, title);
                map.put(TAG_CONTENT, content);
                map.put(TAG_DATE, date);
                map.put(TAG_CAT, cat);
 
                // adding HashList to ArrayList
                newsList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
 
    private void setupTabs() {
        mTabHost.setup(); // you must call this before adding your tabs!
        mTabHost.addTab(newTab(TAB_LATEST, R.string.latest, R.id.tab_1, R.drawable.latest));
        mTabHost.addTab(newTab(TAB_POLITICS, R.string.politics, R.id.tab_2, R.drawable.politics));
        mTabHost.addTab(newTab(TAB_SPORTS, R.string.sports, R.id.tab_3, R.drawable.sports));
        mTabHost.addTab(newTab(TAB_ECONOMICS, R.string.economics, R.id.tab_4, R.drawable.economics));
    }
 
    private TabSpec newTab(String tag, int labelId, int tabContentId, int ImgId) {
        Log.d(TAG, "buildTab(): tag=" + tag);
 
        View indicator = LayoutInflater.from(getActivity()).inflate(
                R.layout.tab,
                (ViewGroup) mRoot.findViewById(android.R.id.tabs), false);
        ((TextView) indicator.findViewById(R.id.text)).setText(labelId);
        ((ImageView) indicator.findViewById(R.id.imageView1)).setImageResource(ImgId);;
 
        TabSpec tabSpec = mTabHost.newTabSpec(tag);
        tabSpec.setIndicator(indicator);
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }
 
    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged(): tabId=" + tabId);
        currentCat = tabId;
        updateCurrentNews();
        if (TAB_LATEST.equals(tabId)) {
        	//mCurrentValues = new String[] { "Android", "iPhone", "WindowsMobile" };
            updateTab(tabId, R.id.tab_1);
            mCurrentTab = 0;
            return;
        }
        
        if (TAB_POLITICS.equals(tabId)) {
        	//mCurrentValues = new String[] {"Blackberry", "WebOS"};
            updateTab(tabId, R.id.tab_2);
            mCurrentTab = 1;
            return;
        }
        
        if (TAB_SPORTS.equals(tabId)) {
        	//mCurrentValues = new String[] {"Ubuntu", "Windows7"};
            updateTab(tabId, R.id.tab_3);
            mCurrentTab = 2;
            return;
        }
        
        if (TAB_ECONOMICS.equals(tabId)) {
        	//mCurrentValues = new String[] {"Max OS X", "Linux", "OS/2"};
            updateTab(tabId, R.id.tab_4);
            mCurrentTab = 3;
            return;
        }
    }
 
    private void updateTab(String tabId, int placeholder) {
        FragmentManager fm = getFragmentManager();
       
        //String[] values = new String[] {"Max OS X", "Linux", "OS/2"};
        if (fm.findFragmentByTag(tabId) == null) {
            fm.beginTransaction()
                    .replace(placeholder, new NewsListFragment(tabId, newsList), tabId)
                    .commit();
        }
    }
 
}