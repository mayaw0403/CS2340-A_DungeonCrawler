package com.example.cs2340project_group_18.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cs2340project_group_18.Model.Attempt;
import com.example.cs2340project_group_18.Model.Player;
import com.example.cs2340project_group_18.R;
import com.example.cs2340project_group_18.View.Leaderboard;
import com.example.cs2340project_group_18.View.MainActivity;

import java.util.Date;
import java.util.List;

public class EndScreen extends AppCompatActivity {
    private TextView leaderboardTextView;
    private TextView recentAttemptTextView;
    private TextView gameOverTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        gameOverTextView = findViewById(R.id.gameOverLabel);

        if (GameScreen.getLost()) {
            gameOverTextView.setText("GAME OVER");
        } else {
            gameOverTextView.setText(("YOU WON!"));
        }

        leaderboardTextView = findViewById(R.id.leaderboardTextView);
        recentAttemptTextView = findViewById(R.id.recentAttemptTextView);

        Attempt recentAttempt = new Attempt(Player.getInstance().getName(),
                Player.getInstance().getScore(), new Date());
        Leaderboard.getInstance().addAttempt(recentAttempt);

        List<Attempt> topScores = Leaderboard.getInstance().getTopAttempts(5);
        displayLeaderboard(topScores);

        displayRecentAttempt(recentAttempt);
    }


    private void displayLeaderboard(List<Attempt> topAttempts) {
        StringBuilder leaderboardText = new StringBuilder();
        for (Attempt attempt : topAttempts) {
            leaderboardText.append(String.format("%s - %d - %s%n",
                    attempt.getPlayerName(), attempt.getScore(), attempt.getDateTime()));
        }
        leaderboardTextView.setText(leaderboardText.toString());
    }

    private void displayRecentAttempt(Attempt recentAttempt) {
        String recentAttemptText = String.format("%s - %d - %s",
                recentAttempt.getPlayerName(),
                recentAttempt.getScore(), recentAttempt.getDateTime());
        recentAttemptTextView.setText(recentAttemptText);
    }

    public void restartGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}