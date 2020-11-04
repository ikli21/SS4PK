package com.example.ss4pk;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class LoadedUserDataActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaded_user_data);
        Init();
    }
    private void Init(){
        mediaPlayer=MediaPlayer.create(this,R.raw.loading);
        mediaPlayer.setVolume(0.2f,0.2f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}
