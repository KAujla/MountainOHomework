package com.hackathon.karan.mountainohomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import static com.hackathon.karan.mountainohomework.DisplayTimerActivity.endTime;

public class MountainClimbingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_climbing);
        //get the values from intent
        Intent climbInfo = getIntent();
        int end[] = climbInfo.getIntArrayExtra(DisplayTimerActivity.endTime);
        String hours = Integer.toString(end[0]% 12);
        String minutes = Integer.toString(end[1]);
        String displayMsg  = "begin Climbing until "+ hours + ":" + minutes;

        TextView timeDis = findViewById(R.id.climbTIme);
        timeDis.setText(displayMsg);
    }
}
