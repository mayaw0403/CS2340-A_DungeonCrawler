package com.example.cs2340project_group_18.Model;
public abstract class PowerUpDecorator implements PowerUp {
    protected PowerUp decoratedPowerUp;

    public PowerUpDecorator(PowerUp decoratedPowerUp) {
        this.decoratedPowerUp = decoratedPowerUp;
    }

    @Override
    public void applyPowerUp(Player player) {
        decoratedPowerUp.applyPowerUp(player);
    }
}