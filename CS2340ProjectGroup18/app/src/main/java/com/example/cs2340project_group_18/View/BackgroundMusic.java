package com.example.cs2340project_group_18.View;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.cs2340project_group_18.R;

public class BackgroundMusic extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myLog", "Start playing");
        mediaPlayer = MediaPlayer.create(this, R.raw.dungeonlevelmusic);
        mediaPlayer.setVolume(1.0f, 1.0f);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public MediaPlayer getMusic() {
        return mediaPlayer;
    }

    public void setMusic(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
