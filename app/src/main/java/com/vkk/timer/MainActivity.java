package com.vkk.timer;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    ImageButton EXIT;




    public void resetTimer()
    {
        textView.setText("00.00");
        seekBar.setProgress(0);
        countDownTimer.cancel();
        button.setText("START");
        seekBar.setEnabled(true);
        counterIsActive=false;

    }

    public void updateTimer(int secondsLeft)
    {
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-minutes*60;
        String secondString=Integer.toString(seconds);
        String minuteString=Integer.toString(minutes);
         if(seconds<=9)
        {
            secondString="0"+secondString;
        }
        if(minutes<=9)
        {
            minuteString="0"+minuteString;
        }

        textView.setText(minuteString+ ":" +secondString);

    }

    public void START(View view)
    {
        if(counterIsActive==false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("STOP");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.retro);
                    mplayer.start();
                }
            }.start();
        }else{

          resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.seekBar);
       textView=(TextView)findViewById(R.id.textView);


       button=(Button)findViewById(R.id.button);
        seekBar.setMax(3600);
        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
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
        EXIT=(ImageButton)findViewById(R.id.EXIT);
        EXIT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
              finish();
              System.exit(0);
            }
        });

    }



}


