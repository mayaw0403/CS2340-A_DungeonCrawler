package com.example.cs2340project_group_18.Model;

import com.example.cs2340project_group_18.ViewModel.GameScreen;

public class EnemyObserver {
    private static Player player = Player.getInstance();
    public static void updatePlayerHealth() {
        if (!player.isInvincible()) {
            switch (GameScreen.getDifficulty()) {
            case "easy":
                player.setHealth(player.getHealth() - 1);
                break;
            case "medium":
                player.setHealth(player.getHealth() - 2);
                break;
            case "hard":
                player.setHealth(player.getHealth() - 3);
                break;
            default:
                break;
            }
        }
        if (player.getHealth() <= 0) {
            noHealthLeft();
        }
    }

    private static void noHealthLeft() {
        GameScreen.setLost(true);
    }
}
