package com.example.cs2340project_group_18.Model;

public class HealthPowerUp implements PowerUp {
    private int healthIncrease;

    public HealthPowerUp(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    @Override
    public void applyPowerUp(Player player) {
        int currentHealth = player.getHealth();
        player.setHealth(currentHealth + healthIncrease);
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
