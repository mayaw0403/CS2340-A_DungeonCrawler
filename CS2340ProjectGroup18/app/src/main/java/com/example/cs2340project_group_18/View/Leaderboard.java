package com.example.cs2340project_group_18.View;

import com.example.cs2340project_group_18.Model.Attempt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private static Leaderboard instance;
    private List<Attempt> attempts;

    private Leaderboard() {
        attempts = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void addAttempt(Attempt attempt) {
        attempts.add(attempt);
        Collections.sort(attempts, (a1, a2) -> Integer.compare(a2.getScore(), a1.getScore()));
    }

    public List<Attempt> getTopAttempts(int count) {
        return attempts.subList(0, Math.min(count, attempts.size()));
    }
}


