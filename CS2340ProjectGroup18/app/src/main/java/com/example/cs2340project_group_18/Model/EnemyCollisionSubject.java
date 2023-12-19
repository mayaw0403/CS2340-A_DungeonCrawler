package com.example.cs2340project_group_18.Model;

import com.example.cs2340project_group_18.View.GameView;

public class EnemyCollisionSubject {
    public static void collision(int x1, int y1, int size,
                          int x2, int y2, int width2, int height2) {
        boolean colided = y1 < y2 + height2 && y1 + size > y2 && x1 < x2 + width2 && x1 + size > x2;
        if (colided) {
            int score = Player.getInstance().getScore() - 5;
            Player.getInstance().setScore((score >= 0 ? score : 0));
            GameView.resetDamageBuffer();
            notifyObserver();
        }
    }

    private static void notifyObserver() {
        EnemyObserver.updatePlayerHealth();
    }
}
