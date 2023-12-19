package com.example.cs2340project_group_18.Model;
import com.example.cs2340project_group_18.ViewModel.ConfigScreen;

import java.util.Date;

public class Attempt {
    private String playerName;
    private int score;
    private Date dateTime;

    public Attempt(String playerName, int score, Date dateTime) {
        this.playerName = ConfigScreen.getName();
        this.score = score;
        this.dateTime = dateTime;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
