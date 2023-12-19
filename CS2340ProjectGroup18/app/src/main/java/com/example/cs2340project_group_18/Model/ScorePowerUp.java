package com.example.cs2340project_group_18.Model;

public class ScorePowerUp implements PowerUp {
    private int scoreIncrease;

    public ScorePowerUp(int scoreIncrease) {
        this.scoreIncrease = scoreIncrease;
    }

    @Override
    public void applyPowerUp(Player player) {
        int currentScore = player.getScore();
        player.setScore(currentScore + scoreIncrease);
    }

    public int getScoreIncrease() {
        return scoreIncrease;
    }
}