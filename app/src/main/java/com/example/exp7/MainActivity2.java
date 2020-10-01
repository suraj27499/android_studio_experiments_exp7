package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;


import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
    Intent myintent;
    Button play,pause,stop,audioplayer;
    VideoView videoView;
    ImageButton p,n;
    Uri uri;
    ArrayList<Uri> vl;
     int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vl=new ArrayList<>();
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v0));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v1));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v2));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v3));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v4));
        vl.add(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v5));


        p = findViewById(R.id.vprev);
        n = findViewById(R.id.vnext);

        stop = findViewById(R.id.vstop);
        videoView=findViewById(R.id.myvideo);
        audioplayer = findViewById(R.id.goto2);
          uri= vl.get(0);
        final MediaController mc = new MediaController(this);
        videoView.setVideoURI(uri);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);
        videoView.start();

        //Media Player - services to play,pause and stop
        //final MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.sample);




        audioplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent = new Intent(getApplicationContext(),MainActivity.class);
                videoView.pause();
                startActivity(myintent);
            }
        });


        p.setOnClickListener(new View.OnClickListener() {//prev button
            @Override
            public void onClick(View view) {
                videoView.pause();

                count--;
                if(count<=0){
                    p.setEnabled(false);

                }
                if(count<0){
                    count = 0;
                }
                if(count>=0 && count<=5) {
                    uri = vl.get(count);
                    videoView.setVideoURI(uri);
                    mc.setAnchorView(videoView);
                    videoView.setMediaController(mc);
                    videoView.start();

                }


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//stop button
                videoView.seekTo(0);
                videoView.pause();
                videoView.setVideoURI(uri);
            }
        });

       n.setOnClickListener(new View.OnClickListener() {//next button
            @Override
            public void onClick(View view) {
                videoView.pause();

                count++;
                if(count>=5){
                    n.setEnabled(false);

                }
                if(count>5){
                    count = 5;
                }
                if(count>=0 && count<=5) {
                    uri = vl.get(count);
                    videoView.setVideoURI(uri);
                    mc.setAnchorView(videoView);
                    videoView.setMediaController(mc);
                    videoView.start();

                }


            }
        });

    }
}