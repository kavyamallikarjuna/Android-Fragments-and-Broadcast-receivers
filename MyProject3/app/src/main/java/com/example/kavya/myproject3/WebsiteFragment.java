package com.example.kavya.myproject3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Kavya on 24-10-2017.
 */

public class WebsiteFragment extends Fragment {

    private static final String TAG = "QuoteFragment";
    private FrameLayout mMonumentsFrameLayout, mWebsiteFrameLayout;


//    private static final String SELECTED_ITEM_POSITION = "ItemPosition";
//    private int mPosition;
//
//    private int someStateValue;
//    private final String SOME_VALUE_KEY = "someValueToSave";

    private WebView mWebsiteView;
    private int mCurrIdx = -1;
    private int mWebsiteArrLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Quote string at position newIndex
    void showWebsiteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mWebsiteArrLen)
            return;


        mCurrIdx = newIndex;
        mWebsiteView = (WebView) getActivity().findViewById(R.id.webview);
        WebSettings webSettings = mWebsiteView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebsiteView.loadUrl(MainActivity.mWebsiteArray[mCurrIdx]);

    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        setRetainInstance(true);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, getClass().getSimpleName() + ":onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        //setMenuVisibility(true);
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        //return inflater.inflate(R.layout.website, container, false);

        return inflater.inflate(R.layout.website, container, false);
    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mWebsiteView = (WebView) getActivity().findViewById(R.id.webview);
        mWebsiteArrLen = MainActivity.mWebsiteArray.length;
        showWebsiteAtIndex(mCurrIdx);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        Log.i(TAG, getClass().getSimpleName() + ":onConfigurationChanged()");
//        super.onConfigurationChanged(newConfig);
//    }


    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

}
