package com.example.acer.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.acer.timerapp.R.id.timerTextView;

public class MainActivity extends AppCompatActivity {

    SeekBar mySeekBar;
    TextView timerTextView;
    boolean counterIsActive=false;
    Button controllerButton;
    CountDownTimer countDownTimer;


      public void reset(){
          timerTextView.setText("0:30");
          mySeekBar.setProgress(30);
          countDownTimer.cancel();
          counterIsActive = false;
          mySeekBar.setEnabled(true);



      }

      public void updateTimer(int secondsLeft){

        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft -minutes*60;
        String secondString = Integer.toString(seconds);
        if(seconds <=9)
        {
            secondString = "0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes)+ ":" +secondString );
    }

     public void controlTimer(View view){

                if(counterIsActive == false)

                {
                       counterIsActive =true;
                       mySeekBar.setEnabled(false);

                    countDownTimer = new CountDownTimer(mySeekBar.getProgress() * 1000 + 100, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            updateTimer((int) millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {

                            reset();
                            MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
                            mplayer.start();
                        }
                    }.start();
                }
                else
                {
                     reset();
                }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mySeekBar = (SeekBar) findViewById(R.id.mySeekBar);
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        Button controllerButton=(Button) findViewById(R.id.controllerButton);
        mySeekBar.setMax(600);
        mySeekBar.setProgress(30);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
