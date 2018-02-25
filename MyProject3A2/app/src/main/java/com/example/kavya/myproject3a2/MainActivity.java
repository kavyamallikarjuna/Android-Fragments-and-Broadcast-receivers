package com.example.kavya.myproject3a2;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class   MainActivity extends Activity {

    private Gallery gallery;
    private ImageView imgView;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public Integer[] Imgid = {
            R.drawable.chicago_opera_house, R.drawable.chicago_theatre, R.drawable.field_museum, R.drawable.john_hancock_center,
            R.drawable.lincoln_park_zoo, R.drawable.magnificent_mile, R.drawable.millenium_park, R.drawable.navy_pier,
            R.drawable.shedd_aquarium, R.drawable.willis_tower};
    private MyBroadcastReceiver MyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void launch_gal(){

        imgView = (ImageView) findViewById(R.id.image1);
        imgView.setImageResource(Imgid[0]);
        gallery = (Gallery) findViewById(R.id.gallery1);
        AddImgAdp adapter = new AddImgAdp(MainActivity.this, Imgid);
        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getBaseContext(), "picture" + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
                // display the images selected
                ImageView imageView = (ImageView) findViewById(R.id.image1);
                imageView.setImageResource(Imgid[position]);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("abc", "def");
        if (ContextCompat.checkSelfPermission(MainActivity.this, "com.example.kavya.KavyasPermission")
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("abb", "des");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"com.example.kavya.KavyasPermission"},
                    REQUEST_CODE_ASK_PERMISSIONS);
            Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED), "FHi");
            return;
        } else {
            launch_gal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0) {
                    // Permission Granted
                    launch_gal();
                } else {
                    Log.i(String.valueOf(grantResults[0]),"gah");
                    Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED),"ghk");
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "GALLERY Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}



