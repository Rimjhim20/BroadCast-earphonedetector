package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  MyReceiver mReceiver  ;
    boolean Microphone_Plugged_in = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiver = new MyReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                int a=2;
                if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
                    a=intent.getIntExtra("state", -1);
                    if(Integer.valueOf(a)==0){
                        Microphone_Plugged_in = false;
                        Toast.makeText(getApplicationContext(),"Earphone not plugged in",Toast.LENGTH_LONG).show();
                    }if(Integer.valueOf(a)==1){
                        Microphone_Plugged_in = true;
                        Toast.makeText(getApplicationContext(),"Earphone plugged in",Toast.LENGTH_LONG).show();
                    }
                }
            }};
        IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver( mReceiver, receiverFilter );

    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        getApplicationContext().registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getApplicationContext().unregisterReceiver(mReceiver);
    }
}