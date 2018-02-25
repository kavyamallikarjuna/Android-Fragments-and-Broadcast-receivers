package com.example.kavya.myproject3;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kavya.myproject3.MonumentsFragment.ListSelectionListener;

public class MainActivity extends ActionBarActivity implements MonumentsFragment.ListSelectionListener{

    public static String[] mMonumentsArray;
    public static String[] mWebsiteArray;

    private  WebsiteFragment mWebsiteFragment ;
    private  static MonumentsFragment mMonumentsFragment;

    private FragmentManager mFragmentManager;
    private FrameLayout mMonumentsFrameLayout, mWebsiteFrameLayout;

    private static final String TAG_RETAINED_FRAGMENT = "MonumentsRetainedFragment";
    private static final String TAG_RETAINED_FRAGMENT2 = "WebsiteRetainedFragment";
    private int mCurrIdx=-1 ;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "QuoteViewerActivity";
    private static final String TOAST_INTENT = "com.example.kavya.myproject3a2";
    private static final String KAVYAS_PERMISSION = "com.example.kavya.KavyasPermission" ;
    boolean isSelected = false;

    final private int REQUEST_CODE_ASK_PERMISSIONSA1 = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //isSelected = false;
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        mMonumentsArray = getResources().getStringArray(R.array.Monuments);
        mWebsiteArray = getResources().getStringArray(R.array.Website);

        setContentView(R.layout.activity_main);

        mMonumentsFrameLayout = (FrameLayout) findViewById(R.id.monument_fragment_container);
        mWebsiteFrameLayout = (FrameLayout) findViewById(R.id.website_fragment_container);

        mFragmentManager = getFragmentManager();
        mMonumentsFragment =(MonumentsFragment) getFragmentManager().findFragmentByTag(TAG_RETAINED_FRAGMENT);

        if(null==mMonumentsFragment) {
            mMonumentsFragment = new MonumentsFragment();
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.monument_fragment_container, mMonumentsFragment, TAG_RETAINED_FRAGMENT).commit();
        mFragmentManager.executePendingTransactions();
        if (null!=savedInstanceState && savedInstanceState.getInt("SELECTED_INDEX")>=0) {
            mCurrIdx = savedInstanceState.getInt("SELECTED_INDEX");
        }
        setLayout(isSelected||(mCurrIdx>=0));

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                isSelected=false;
                setLayout(isSelected);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        if (mCurrIdx >= 0) {
            ((MonumentsFragment)mMonumentsFragment).getListView().setItemChecked(mCurrIdx, true);
            onListSelection(mCurrIdx);
        }
    }

    private void setLayout(boolean isSelected) {
        // Set the layout for both the Website and Monuments has been added
        boolean showMonuments = !isSelected || Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation;
        boolean showWebsite = isSelected;
        if (showMonuments && !showWebsite) {
            mMonumentsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mWebsiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        } else if(showMonuments && showWebsite){
            mMonumentsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            mWebsiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2f));
        } else if (showWebsite) {
            mWebsiteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mMonumentsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }
    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {
        mWebsiteFragment = (WebsiteFragment)getFragmentManager().findFragmentByTag(TAG_RETAINED_FRAGMENT2);
        mCurrIdx = index;
        if(null==mWebsiteFragment) {
            mWebsiteFragment = new WebsiteFragment();
        }
        if(!mWebsiteFragment.isAdded()) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.website_fragment_container, mWebsiteFragment, TAG_RETAINED_FRAGMENT2)
                    .addToBackStack(TAG_RETAINED_FRAGMENT2)
                    .commit();
            fm.executePendingTransactions();
        }

        isSelected = true;
        ((WebsiteFragment) mWebsiteFragment).showWebsiteAtIndex(index);
        setLayout(isSelected);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("SELECTED_INDEX", mCurrIdx) ;
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();

    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exita1:
                System.exit(0);
                return true;
            case R.id.launchinga2:
                if (ContextCompat.checkSelfPermission(MainActivity.this, "com.example.kavya.KavyasPermission")
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.i("abb", "des");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"com.example.kavya.KavyasPermission"},
                            REQUEST_CODE_ASK_PERMISSIONSA1);;
                    Log.i("kavyam","FHi");
                    return true;
                }
                else {
                    Intent intent = new Intent();
                    intent.setAction(TOAST_INTENT);
                    Log.i("bcd","ecf");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    Log.i("abc","dec");
                    sendBroadcast(intent,KAVYAS_PERMISSION);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONSA1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0) {
                    // Permission Granted
                    Intent intent = new Intent();
                    intent.setAction(TOAST_INTENT);
                    Log.i("bcd","ecf");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    Log.i("abc","dec");
                    sendOrderedBroadcast(intent,KAVYAS_PERMISSION);
                } else {
                    Log.i("Galll","gah");
                    Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED),"ghk");
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "GALLERY DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}