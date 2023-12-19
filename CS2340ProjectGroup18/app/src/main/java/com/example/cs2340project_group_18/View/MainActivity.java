package com.example.cs2340project_group_18.View;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import com.example.cs2340project_group_18.ViewModel.ConfigScreen;
import com.example.cs2340project_group_18.R;
import com.google.android.material.snackbar.Snackbar;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        Button musicButton = findViewById(R.id.musicButton);
        Button exitButton = findViewById(R.id.exitButton);
        Button controlButton = findViewById(R.id.instructButton);

        exitButton.setOnClickListener(v -> finish());

        playButton.setOnClickListener(v -> openConfigScreen());

        musicButton.setOnClickListener(v -> controlMusic());

        controlButton.setOnClickListener(v -> showSnackbar());
    }


    public void openConfigScreen() {
        Intent intent = new Intent(MainActivity.this, ConfigScreen.class);
        startActivity(intent);
    }

    private void showSnackbar() {
        RelativeLayout layout = (findViewById(R.id.configScreenLayout));
        Snackbar.make(layout, "Movement: Arrow Keys" + "\n" + "Action: Space bar - Shoot/Attack",
                Snackbar.LENGTH_SHORT).show();
    }

    private void controlMusic() {
        if (isPlaying) {
            stopService(new Intent(MainActivity.this, BackgroundMusic.class));
        } else {
            startService(new Intent(MainActivity.this, BackgroundMusic.class));
        }
        isPlaying = !isPlaying;
    }
}