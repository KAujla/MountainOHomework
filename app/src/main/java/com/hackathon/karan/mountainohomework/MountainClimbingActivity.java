package com.hackathon.karan.mountainohomework;

        import android.content.Intent;
        import android.graphics.drawable.AnimationDrawable;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;

public class MountainClimbingActivity extends AppCompatActivity {
    AnimationDrawable climbAnimation;
    ImageView ClimbImage;
    AnimationDrawable victoryAnimation;
    ImageView victoryImage;

    ProgressBar climbProgress;
    TextView timeDis;
    Boolean timerComplete = false;
    int totaltime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_climbing);
        //instantiate the animation for climbing
        ClimbImage = (ImageView) findViewById(R.id.animation);
        ClimbImage.setBackgroundResource(R.drawable.climb_animation);
        climbAnimation = (AnimationDrawable) ClimbImage.getBackground();

        //instantiate the image for the finish pose
        victoryImage = findViewById(R.id.victoryView);
        victoryImage.setBackgroundResource(R.drawable.victory_pose);
        victoryAnimation = (AnimationDrawable) victoryImage.getBackground();

        //get the values from intent
        Intent climbInfo = getIntent();
        totaltime = climbInfo.getIntExtra(DisplayTimerActivity.endTime,0);

        //display total number of minutes the user intends to work for 
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
                //get the current time in seconds
                int currentSeconds= (int) millisecondsUntilFinished/intervalStep;

                //find and remove the number of minutes
                int currentMinutes = (currentSeconds/60);
                currentSeconds= currentSeconds % 60;

                //find and remove the current hours from minutes
                int currentHours = currentMinutes/60;
                currentMinutes = currentMinutes % 60;

                //diplay them
                timeDis.setText(getString(R.string.countdownText) + currentHours + "h:"+ currentMinutes + "m:" + currentSeconds + "s");
                currentProgress = totaltime - ((int)millisecondsUntilFinished/intervalStep);
                climbProgress.setProgress(currentProgress, true);
            }
            public void onFinish(){
                //end the climb animation and make it invisible
                climbAnimation.stop();
                ClimbImage.setVisibility(View.INVISIBLE);

                //make the victory image visible and animate it
                victoryImage.setVisibility(View.VISIBLE);
                victoryAnimation.start();
                timeDis.setText(R.string.climb_Completion);
                timerComplete = true;
            }
        }.start();
        climbAnimation.start();

    }
}
