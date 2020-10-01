package com.example.exp7;
//Add audio and video files to res/raw folder(i.e .mp3,.mp4)




import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {
    Button start,p,videoPlayer;

    Intent myintent;
    String playPause;
    SeekBar seekBar,volBar;
    boolean wasPlaying = false;
    FloatingActionButton fab;
     MediaPlayer m;
     AudioManager audioManager;//volume
    ImageButton prevB,nextB;
    Uri uri;
    ArrayList<Uri> vl;
    int count=0,x=0;
    String name = "v0";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vl=new ArrayList<>();
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a0));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a1));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a2));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a3));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a4));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a5));
        uri = vl.get(0);

        prevB = findViewById(R.id.prev);
        nextB = findViewById(R.id.next);
        fab = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);
        volBar = findViewById(R.id.seekBar2);
        videoPlayer = findViewById(R.id.goto1);
        //boolean wasPlaying = false;
        m = MediaPlayer.create(getApplicationContext(),uri);

        playPause = "pause";
        //volume control

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volBar.setMax(maxVolume);
        volBar.setProgress(currentVolume);
        volBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });







        fab.setOnClickListener(new View.OnClickListener() {//single play/pause button
            @Override
            public void onClick(View view) {
                if(playPause.equals("pause")){
                    fab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause));
                    m.start();
                    playPause = "play";

                }else{
                    playPause = "pause";
                    fab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
                    m.pause();
                }

            }
        });


        final SeekBar SeekBar = (SeekBar) findViewById(R.id.seekBar);

        SeekBar.setMax(m.getDuration());//mapping

        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               // Log.i(" seekbar moved", Integer.toString(i));

                m.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Timer t = new Timer();//creatinga timer

        TimerTask tt = new TimerTask() {//creating a task
            @Override
            public void run() {
                SeekBar.setProgress(m.getCurrentPosition());
            }
        };
        t.scheduleAtFixedRate(tt,0,5000 );//calling the task in sync with  timer at fixed rate


      /*  new Timer().scheduleAtFixedRate(new TimerTask() {//without creating object of timer class
            @Override
            public void run() {

                SeekBar.setProgress(m.getCurrentPosition());

            }
        }, 0, 5000);*/


      videoPlayer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              myintent = new Intent(getApplicationContext(),MainActivity2.class);
              playPause = "pause";
              fab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
              m.pause();
              startActivity(myintent);
          }
      });
        prevB.setOnClickListener(new View.OnClickListener() {//prev button
            @Override
            public void onClick(View view) {
                m.pause();
                playPause = "pause";
                count--;
                if(count<=0){
                    nextB.setEnabled(false);
                    count = 0;
                }
                if(count<0){
                    count=0;
                }
                if(count>=0 && count<=5){
                    uri = vl.get(count);
                    m = MediaPlayer.create(getApplicationContext(),uri);
                    fab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
                }

            }
        });
        nextB.setOnClickListener(new View.OnClickListener() {//next button
            @Override
            public void onClick(View view) {
                m.pause();
                playPause = "pause";
                count++;
                if(count>=5){
                    nextB.setEnabled(false);

                }
                if(count>5){
                    count = 5;
                }
                if(count>=0 && count<=5) {
                    uri = vl.get(count);
                    m = MediaPlayer.create(getApplicationContext(), uri);
                    fab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
                }


            }
        });
    }

}