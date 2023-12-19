package com.example.cs2340project_group_18.Model;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPowerUp implements PowerUp {
    private int durationInSeconds;

    public InvincibilityPowerUp(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public void applyPowerUp(Player player) {
        player.setScore(player.getScore() + 1);
        player.setInvincible(true);
        // Start a timer or perform actions for the duration of invincibility
        // You can use a TimerTask, CountDownTimer, or any other timer mechanism
        // After the specified duration, make the player vulnerable again
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setInvincible(false);
            }
        }, durationInSeconds * 1000); // Convert duration to milliseconds
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }
}
