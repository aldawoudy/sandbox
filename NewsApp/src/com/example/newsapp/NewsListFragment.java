package com.example.newsapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
 
@SuppressLint("ValidFragment")
public class NewsListFragment extends ListFragment  {
 
    private static final String TAG = "FragmentTabs";
    
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_DATE = "date";
    private static final String TAG_CAT = "cat";
 
    
    private String mTag;
    private String[] mValues;
    private ArrayList<HashMap<String, String>> mNewsList;


	private String[] mFrom;


	private int[] mTo;
    /*
    //private MyAdapter mAdapter;
    private ArrayList<String> mItems;
    private LayoutInflater mInflater;
    private int mTotal;
    private int mPosition;
 
    private static final String[] WORDS = { "Lorem", "ipsum", "dolor", "sit",
            "amet", "consectetur", "adipiscing", "elit", "Fusce", "pharetra",
            "luctus", "sodales" };
    private static final String[] NUMBERS = { "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV" };
 
    private static final int SLEEP = 100;
 
    private final int wordBarColor = R.color.word_bar;
    private final int numberBarColor = R.color.number_bar;
    */
    public NewsListFragment() {
    }
 
    public NewsListFragment(String tag, ArrayList<HashMap<String, String>> newsList) {
        mTag = tag;
        mNewsList = newsList;
        mFrom = new String[] {TAG_TITLE, TAG_DATE };
        mTo = new int[] {R.id.title, R.id.date};
        
        mValues = new String[mNewsList.size()];
        for(int i = 0 ; i < mNewsList.size(); i++) {
        	HashMap<String, String> map = mNewsList.get(i);
        	mValues[i] = map.get("title");
        }
        //mTotal = TabsFragment.TAB_LATEST.equals(mTag) ? WORDS.length : NUMBERS.length;
 
        Log.d(TAG, "Constructor: tag=" + tag);
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);

      SimpleAdapter adapter = new SimpleAdapter(getActivity(), mNewsList, R.layout.list_item, mFrom, mTo);
      //ListAdapter adapter = new SimpleAdapter(mActivity, mNewsList, mBackStackNesting, mValues, null)
    		  
    		  /*
    		  new SimpleAdapter(this, mNewsList,
    		                  R.layout.list_item,
    		                  new String[] { TAG_NAME, TAG_EMAIL, TAG_PHONE_MOBILE }, new int[] {
    		                          R.id.name, R.id.email, R.id.mobile });*/
      
      /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
          android.R.layout.simple_list_item_1, mValues);*/
      setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
      // Do something with the data

    }
}
