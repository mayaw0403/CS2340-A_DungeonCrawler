package com.example.cs2340project_group_18.Model;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.cs2340project_group_18.ViewModel.ConfigScreen;
import com.example.cs2340project_group_18.ViewModel.GameScreen;

public class Player {
    private static Player uniquePlayer;
    private static Integer health;
    private static int score;
    private static Integer speed;
    private boolean invincible;
    private static String name;
    private static Drawable playerImage;
    private MovementStrategy movementStrategy;
    private int x;
    private int oldX;
    private int y;
    private int oldY;
    private int screenWidth = 828;
    private int screenHeight = 1792;
    private Room room;
    private PowerUp powerUp;

    private Player(String name, Integer health) {
        this.health = health;
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.oldX = this.x;
        this.oldY = this.y;
        playerImage = ConfigScreen.getCharacterSelected();
        this.speed = 0; // or any default value
    }

    public static Player getInstance() {
        if (uniquePlayer == null) {
            synchronized (Player.class) {
                if (uniquePlayer == null) {
                    uniquePlayer = new Player(ConfigScreen.getName(), GameScreen.getHealth());
                    uniquePlayer.speed = 0; // or any default value
                }
            }
        }
        return uniquePlayer;
    }
    public void collectHealthPowerUp(HealthPowerUp powerUp) {
        // Assuming the HealthPowerUp class has a method
        // getHealthIncrease() to retrieve the health increase value
        int healthIncrease = powerUp.getHealthIncrease();
        this.health += healthIncrease;
    }

    public void collectScorePowerUp(ScorePowerUp powerUp) {
        // Assuming the ScorePowerUp class has a method
        // getScoreIncrease() to retrieve the score increase value
        Log.d("Power-up", "Score power-up collected in Player class");
        int scoreIncrease = powerUp.getScoreIncrease();
        this.score += scoreIncrease;
    }

    public void collectInvincibilityPowerUp(InvincibilityPowerUp powerUp) {
        setInvincible(true);
        powerUp.applyPowerUp(this);
    }


    public void usePowerUp() {
        if (powerUp != null) {
            powerUp.applyPowerUp(this);
            powerUp = null; // Power-up used, nullify it
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public boolean isWithinScreenBoundaries(int x, int y) {
        return x >= 0 && x < screenWidth && y >= 0 && y < screenHeight;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public Room getCurrentRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public static String getName() {
        return name;
    }
    public static void setPlayerImage() {
        playerImage = ConfigScreen.getCharacterSelected();
    }
    public static Drawable getPlayerImage() {
        return playerImage;
    }

    public void setOldX(int x) {
        this.oldX = x;
    }

    public void setOldY(int y) {
        this.oldY = y;
    }
    public int getOldY() {
        return oldY;
    }
    public int getOldX() {
        return oldX;
    }

    public Integer getHealth() {
        return this.health;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(Integer score) {

        this.score = score;
    }

    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(Integer speed) {

        this.speed = speed;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
}
