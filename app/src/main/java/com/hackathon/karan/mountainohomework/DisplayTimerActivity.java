package com.hackathon.karan.mountainohomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DisplayTimerActivity extends AppCompatActivity {
    public static final String endTime = "com.hackathon.karan.mountainohomework.TIME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_timer);



        Intent timerIntent = getIntent();
        String displayMessage = timerIntent.getStringExtra(MainActivity.Test_Message);
        TextView display = findViewById(R.id.HowToStart);
        display.setText(displayMessage);
    }
    //called, when the button is tapped
    public void beginClimb(View view){
        //get the current settings on the time
        TimePicker workTimer = findViewById(R.id.workTimer);

        //get the current time
        GregorianCalendar currTime = new GregorianCalendar();
        TimeZone currTZ = currTime.getTimeZone();

        int hourOffset = (workTimer.getHour() % 12) - currTime.get(GregorianCalendar.HOUR);
        int minuteOffset = workTimer.getMinute() - currTime.get(GregorianCalendar.MINUTE);
        //Log.d("time", "workTimer.getHour() = " + workTimer.getHour());
        //Log.d("time", "currTime.get(GregorianCalendar.HOUR) = " + currTime.get(GregorianCalendar.HOUR));
        if ((hourOffset < 0) || (minuteOffset < 0)){
            Log.e("TimeError", "the time negative!");
        }
        int workTime = ((hourOffset * 60) + minuteOffset)*60;//the total time the timer will run in seconds

        Intent beginClimbing = new Intent(this, MountainClimbingActivity.class);
        beginClimbing.putExtra(endTime, workTime);
        startActivity(beginClimbing);
    }
}
