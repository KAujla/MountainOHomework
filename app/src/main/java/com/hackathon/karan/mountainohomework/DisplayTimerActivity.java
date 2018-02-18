package com.hackathon.karan.mountainohomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

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
        int hoursAndMinutes[] = new int[2];
        hoursAndMinutes[0] = workTimer.getHour();
        hoursAndMinutes[1] = workTimer.getMinute();
        Intent beginClimbing = new Intent(this, MountainClimbingActivity.class);
        beginClimbing.putExtra(endTime, hoursAndMinutes);
        startActivity(beginClimbing);
    }
}
