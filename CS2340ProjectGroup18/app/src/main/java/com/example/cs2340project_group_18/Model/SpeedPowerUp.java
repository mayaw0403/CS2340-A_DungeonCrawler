package com.example.cs2340project_group_18.Model;

public class SpeedPowerUp implements PowerUp {
    private int speedIncrease;

    public SpeedPowerUp(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }

    @Override
    public void applyPowerUp(Player player) {
        int currentSpeed = player.getSpeed();
        player.setSpeed(currentSpeed + speedIncrease);
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }
}