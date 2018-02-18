package com.hackathon.karan.mountainohomework;

        import android.content.Intent;
        import android.graphics.drawable.AnimationDrawable;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;

public class MountainClimbingActivity extends AppCompatActivity {
    AnimationDrawable climbAnimation;
    ProgressBar climbProgress;
    TextView timeDis;
    int totaltime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_climbing);
        //instantiate the animation
        ImageView ClimbImage = (ImageView) findViewById(R.id.animation);
        ClimbImage.setBackgroundResource(R.drawable.climb_animation);
        climbAnimation = (AnimationDrawable) ClimbImage.getBackground();

        //get the values from intent
        Intent climbInfo = getIntent();
        final int totaltime = climbInfo.getIntExtra(DisplayTimerActivity.endTime,0);

        String displayMsg  = "begin Work for " + totaltime + " seconds" ;
        timeDis = findViewById(R.id.climbTime);
        timeDis.setText(displayMsg);

        climbProgress = findViewById(R.id.climbStatus);
        //set the max value of the progressbar to the number of minutes the user intends to work for
        climbProgress.setMax(totaltime);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        int countDownTime = totaltime *1000;//convert the time into milliseconds
        final int intervalStep = 1000;//using 1 minute intervals

        new CountDownTimer(countDownTime, intervalStep){
            int currentProgress = 0;
            public void onTick(long millisecondsUntilFinished){
                //every minute increase the progressbar
                timeDis.setText("time left" + millisecondsUntilFinished/intervalStep);
                currentProgress = totaltime - ((int)millisecondsUntilFinished/intervalStep);
                climbProgress.setProgress(currentProgress, true);
            }
            public void onFinish(){
                timeDis.setText(R.string.climb_Completion);
            }
        }.start();
        climbAnimation.start();

    }
}
