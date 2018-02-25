package com.example.kavya.myproject3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Kavya on 27-10-2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        //if(intent.getAction().equals("com.example.kavya.myproject3a2")) {

            //Toast.makeText(context, "Data Received from External App", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(context.getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        myIntent.putExtras(bundle);
        myIntent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.getApplicationContext().startActivity(myIntent);
//            Intent i=new Intent(context,MainActivity.class);
//            context.startActivity(i);

        }
}


